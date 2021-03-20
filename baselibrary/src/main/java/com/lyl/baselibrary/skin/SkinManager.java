package com.lyl.baselibrary.skin;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.lyl.baselibrary.skin.attr.SkinView;
import com.lyl.baselibrary.skin.callback.ISkinChangeListener;
import com.lyl.baselibrary.skin.config.SkinConfig;
import com.lyl.baselibrary.skin.config.SkinPreUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/3/25.
 * Version 1.0
 * Description: 皮肤的管理类
 */
public class SkinManager {


    private static SkinManager mInstance;

    static {
        mInstance = new SkinManager();
    }

    private Context mContext;
    private SkinResource mSkinResource;
    private Map<ISkinChangeListener, List<SkinView>> mSkinViews = new HashMap<>();

    public static SkinManager getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();

        // 每一次打开应用都会到这里来，防止皮肤被任意删除，做一些措施
        String currentSkinPath = SkinPreUtils.getInstance(context).getSkinPath();

        File file = new File(currentSkinPath);

        if (!file.exists()) {
            // 不存在，清空皮肤
            SkinPreUtils.getInstance(context).clearSkinInfo();
            return;
        }

        // 最好做一下  能不能获取到包名
        String packageName = context.getPackageManager().getPackageArchiveInfo(
                currentSkinPath, PackageManager.GET_ACTIVITIES).packageName;

        if (TextUtils.isEmpty(packageName)) {
            SkinPreUtils.getInstance(context).clearSkinInfo();
            return;
        }

        // 最好校验签名  增量更新再说


        // 做一些初始化的工作,
        mSkinResource = new SkinResource(mContext, currentSkinPath);
    }


    /**
     * 加载皮肤
     *
     * @param skinPath
     * @return
     */
    public int loadSkin(String skinPath) {

        File file = new File(skinPath);

        if (!file.exists()) {
            // 不存在，清空皮肤
            return SkinConfig.SKIN_FILE_NOEXSIST;
        }

        // 最好做一下  能不能获取到包名
        String packageName = mContext.getPackageManager().getPackageArchiveInfo(
                skinPath, PackageManager.GET_ACTIVITIES).packageName;

        if (TextUtils.isEmpty(packageName)) {
            return SkinConfig.SKIN_FILE_ERROR;
        }

        // 1. 当前皮肤如果一样不要换
        String currentSkinPath = SkinPreUtils.getInstance(mContext).getSkinPath();

        if (skinPath.equals(currentSkinPath)) {
            return SkinConfig.SKIN_CHANGE_NOTHING;
        }


        // 校验签名  增量更新再说

        // 最好把他复制走，用户不能轻易删除的地方  cache目录下面

        // 初始化资源管理
        mSkinResource = new SkinResource(mContext, skinPath);

        // 改变皮肤
        changeSkin();

        // 保存皮肤的状态
        saveSkinStatus(skinPath);

        return SkinConfig.SKIN_CHANGE_SUCCESS;
    }

    /**
     * 改变皮肤
     */
    private void changeSkin() {
        Set<ISkinChangeListener> keys = mSkinViews.keySet();

        for (ISkinChangeListener key : keys) {
            List<SkinView> skinViews = mSkinViews.get(key);
            for (SkinView skinView : skinViews) {
                skinView.skin();
            }

            // 通知Activity
            key.changeSkin(mSkinResource);
        }
    }

    private void saveSkinStatus(String skinPath) {
        // 如果用上次写好的数据库，不了解，
        // 致命一点，不要嵌套
        SkinPreUtils.getInstance(mContext).saveSkinPath(skinPath);
    }

    /**
     * 恢复默认
     *
     * @return
     */
    public int restoreDefault() {
        // 判断当前有没有皮肤，没有皮肤就不要执行任何方法
        String currentSkinPath = SkinPreUtils.getInstance(mContext).getSkinPath();

        if (TextUtils.isEmpty(currentSkinPath)) {
            return SkinConfig.SKIN_CHANGE_NOTHING;
        }

        // 当前手机运行的app的路径apk路径
        String skinPath = mContext.getPackageResourcePath();
        // 初始化资源管理
        mSkinResource = new SkinResource(mContext, skinPath);

        // 改变皮肤
        changeSkin();

        // 把皮肤信息清空
        SkinPreUtils.getInstance(mContext).clearSkinInfo();

        return SkinConfig.SKIN_CHANGE_SUCCESS;
    }

    /**
     * 获取SkinView通过activity
     *
     * @param activity
     * @return
     */
    public List<SkinView> getSkinViews(Activity activity) {
        return mSkinViews.get(activity);
    }

    /**
     * 注册
     *
     * @param skinChangeListener
     * @param skinViews
     */
    public void register(ISkinChangeListener skinChangeListener, List<SkinView> skinViews) {
        mSkinViews.put(skinChangeListener, skinViews);
    }

    /**
     * 获取当前的皮肤资源
     *
     * @return
     */
    public SkinResource getSkinResource() {
        return mSkinResource;
    }

    /**
     * 检测要不要换肤
     *
     * @param skinView
     */
    public void checkChangeSkin(SkinView skinView) {
        // 如果当前有皮肤，也就是保存了皮肤路径，就换一下皮肤
        String currentSkinPath = SkinPreUtils.getInstance(mContext).getSkinPath();

        if (!TextUtils.isEmpty(currentSkinPath)) {
            // 切换一下
            skinView.skin();
        }
    }

    /**
     * 防止内存泄露
     */
    public void unregister(ISkinChangeListener skinChangeListener) {
        mSkinViews.remove(skinChangeListener);
    }
}
