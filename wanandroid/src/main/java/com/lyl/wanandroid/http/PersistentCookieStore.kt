package com.lyl.wanandroid.http

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import okhttp3.Cookie
import okhttp3.HttpUrl
import java.io.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * User: lyl
 * Date: 2019-07-30 14:41
 */
class PersistentCookieStore(context: Context) {
    private val COOKIE_PREFS = "Cookies_Prefs"
    private val LOG_TAG = "PersistentCookieStore"
    private val cookiePrefs: SharedPreferences
    private val cookies: HashMap<String, ConcurrentHashMap<String, Cookie>>

    init {
        cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0)
        cookies = HashMap()

        //将持久化的cookies缓存到内存中，即map中
        val prefsMap = cookiePrefs.all
        for ((key, value) in prefsMap) {
            val cookieNames = TextUtils.split(value as String, ",")

            for (name in cookieNames) {
                val encodedCookie = cookiePrefs.getString(name, null)

                if (!TextUtils.isEmpty(encodedCookie)) {
                    val decodeCookie = decodeCookie(encodedCookie)
                    if (null != decodeCookie) {
                        cookies[key] = ConcurrentHashMap()
                    }
                    if (key != null && decodeCookie != null) {
                        cookies[key]!![name] = decodeCookie
                    }
                }
            }
        }
    }


    /**
     * 获取cookieToken
     *
     * @param cookie
     * @return
     */
    protected fun getCookieToken(cookie: Cookie): String {
        return cookie.name() + "@" + cookie.domain()
    }

    /**
     * 添加到持久化
     *
     * @param url
     * @param cookie
     */
    fun add(url: HttpUrl, cookie: Cookie) {
        val name = getCookieToken(cookie)

        //将cookies缓存到内存中，如果缓存过期，就重置此cookie
        if (!cookie.persistent()) {
            if (!cookies.containsKey(url.host())) {
                cookies[url.host()] = ConcurrentHashMap()
            }
            cookies[url.host()]!![name] = cookie
        } else {
            if (cookies.containsKey(url.host())) {
                cookies[url.host()]!!.remove(name)
            }
        }
        //将cookies持久化到本地
        val prefsWriter = cookiePrefs.edit()
        prefsWriter.putString(url.host(), TextUtils.join(",", cookies[url.host()]!!.keys))
        prefsWriter.putString(name, encodeCookie(SerializableOkHttpCookies(cookie)))
        prefsWriter.apply()

    }

    /**
     * 通过url得到存储cookie集合
     *
     * @param url
     * @return
     */
    operator fun get(url: HttpUrl): List<Cookie> {
        val ret = ArrayList<Cookie>()
        if (cookies.containsKey(url.host()))
            ret.addAll(cookies[url.host()]!!.values)
        return ret
    }

    /**
     * 清除cookies本地持久化
     *
     * @return
     */
    fun removeAll(): Boolean {
        val prefsWriter = cookiePrefs.edit()
        prefsWriter.clear()
        prefsWriter.apply()
        cookies.clear()
        return true
    }

    /**
     * 获取cookie集合
     *
     * @return
     */
    fun getCookies(): List<Cookie> {
        val ret = ArrayList<Cookie>()
        for (key in cookies.keys)
            ret.addAll(cookies[key]!!.values)

        return ret
    }

    /**
     * cookies 序列化成 string
     *
     * @param cookie 要序列化的cookie
     * @return 序列化之后的string
     */
    protected fun encodeCookie(cookie: SerializableOkHttpCookies?): String? {
        if (cookie == null)
            return null
        val os = ByteArrayOutputStream()
        try {
            val outputStream = ObjectOutputStream(os)
            outputStream.writeObject(cookie)
        } catch (e: IOException) {
            Log.d(LOG_TAG, "IOException in encodeCookie", e)
            return null
        }

        return byteArrayToHexString(os.toByteArray())
    }

    /**
     * 将字符串反序列化成cookies
     *
     * @param cookieString cookies string
     * @return cookie object
     */
    protected fun decodeCookie(cookieString: String?): Cookie? {
        val bytes = hexStringToByteArray(cookieString)
        val byteArrayInputStream = ByteArrayInputStream(bytes)
        var cookie: Cookie? = null
        try {
            val objectInputStream = ObjectInputStream(byteArrayInputStream)
            cookie = (objectInputStream.readObject() as SerializableOkHttpCookies).getCookies()
        } catch (e: IOException) {
            Log.d(LOG_TAG, "IOException in decodeCookie", e)
        } catch (e: ClassNotFoundException) {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e)
        }

        return cookie
    }

    /**
     * 二进制数组转十六进制字符串
     *
     * @param bytes byte array to be converted
     * @return string containing hex values
     */
    protected fun byteArrayToHexString(bytes: ByteArray): String {
        val sb = StringBuilder(bytes.size * 2)
        for (element in bytes) {
            val v:Int = element.toInt() and 0xff
            if (v < 16) {
                sb.append('0')
            }
            sb.append(Integer.toHexString(v))
        }
        return sb.toString().toUpperCase(Locale.US)
    }

    /**
     * 十六进制字符串转二进制数组
     *
     * @param hexString string of hex-encoded values
     * @return decoded byte array
     */
    protected fun hexStringToByteArray(hexString: String?): ByteArray {
        val len = hexString!!.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(hexString[i], 16) shl 4) + Character.digit(hexString[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

}
