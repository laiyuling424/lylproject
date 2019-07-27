package com.lyl.mvptest.mvp.huanfu

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import java.lang.Exception

/**
 * User: lyl
 * Date: 2019-07-27 11:04
 */
class SkinManager {

    companion object {
        private var skinManager: SkinManager = SkinManager()

        public fun getSkinManager(): SkinManager {
            return skinManager
        }
    }

    private var resources: Resources? = null
    private var context: Context? = null
    private var skinPackageName: String? = null

    public fun setContext(context: Context) {
        this.context = context
    }

    public fun loadSkinApk(path: String) {

        var packageManager: PackageManager = context!!.packageManager
        var packageArchiveInfo: PackageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)
        try {

            skinPackageName = packageArchiveInfo.packageName
            val assetManager = AssetManager::class.java.newInstance()

            val addAssetPath = assetManager.javaClass.getMethod("addAssetPath", String::class.java)
            addAssetPath.invoke(assetManager, path)
            resources = Resources(assetManager, context!!.resources.displayMetrics, context!!.resources.configuration)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 根据传进来的id 或匹配皮肤插件apk资源对象 如果有类型和名字都一样的就返回
     */
    public fun getColor(id: Int): Int {
        if (resources == null) {
            return id
        }
        //android:background="@color/zzz"
        // zzz
        var resourceEntryName = context!!.resources.getResourceEntryName(id)
        // color
        var typeName = context!!.resources.getResourceTypeName(id)
        //就是名字和类型匹配的资源对象中的id
        val identifier = resources!!.getIdentifier(resourceEntryName, typeName, skinPackageName)

        if (identifier == 0) {
            return id
        }
        return resources!!.getColor(identifier)
    }

    public fun getDrawable(id: Int): Drawable {
        if (resources == null) {
            return ContextCompat.getDrawable(context!!, id)!!
        }
        var resourceEntryName = context!!.resources.getResourceEntryName(id)
        // color
        var typeName = context!!.resources.getResourceTypeName(id)
        //就是名字和类型匹配的资源对象中的id
        val identifier = resources!!.getIdentifier(resourceEntryName, typeName, skinPackageName)

        Log.d("lyll","identifier============$identifier")
        if (identifier == 0) {
            return ContextCompat.getDrawable(context!!, id)!!
        }
        return return resources!!.getDrawable(identifier)
    }

}