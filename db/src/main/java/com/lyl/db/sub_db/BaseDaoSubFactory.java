package com.lyl.db.sub_db;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.dds.dbframwork.BuildConfig;
import com.dds.dbframwork.db.BaseDao;
import com.dds.dbframwork.db.DaoFactory;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 5/14/21 8:48 AM
 * Describe:数据库分库的工厂类
 */

public class BaseDaoSubFactory extends DaoFactory {

    //单例
    private static final BaseDaoSubFactory instance = new BaseDaoSubFactory();
    //数据库文件统一存放路径
    private static final String def_db_path = "data/data/" + BuildConfig.APPLICATION_ID;
    //数据库连接池，缓存分库的连接池
    private Map<String, BaseDao> dbGroup = Collections.synchronizedMap(new HashMap<String, BaseDao>());

    public static BaseDaoSubFactory getInstance() {
        return instance;
    }

    /**
     * 获得分库的操作dao
     *
     * @param daoClass    dao的class对象
     * @param entityClass 对应表的class对象
     * @param <T>         继承basedao的dao对象的类型
     * @param <M>         传递进dao的表对应的java实体类的类型
     * @param m           传递进dao的表对应的java实体类，必须包含唯一约束的主键
     * @return 数据库分库操作dao对象
     */
    public <T extends BaseDao<M>, M> T getSubDao(Class<T> daoClass, Class<M> entityClass) {
        //在主数据库里先获取到basedao对象
        T baseDao = BaseDaoSubFactory.getInstance().getBaseDao(daoClass, entityClass);
        //在主数据库里获取到了对象才能创建分库
        if (baseDao != null) {
            //创建分库数据库
            String dbPath = null;
            try {
                dbPath = getSeparateTablePath(baseDao, entityClass.newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            //如果缓存有，则直接取缓存中的数据库操作对象
            if (dbGroup.get(dbPath) != null) {
                return (T) dbGroup.get(dbPath);
            }
            //没有缓存，则创建
            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
            try {
                //获得dao的对象，并初始化
                baseDao = daoClass.newInstance();
                baseDao.init(sqLiteDatabase, entityClass);
                //添加缓存
                dbGroup.put(dbPath, baseDao);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return baseDao;
        }
        return null;
    }

    //创建当前用户的分数据库路径
    public <M> String getSeparateTablePath(BaseDao<M> baseDao, M m) {
        String separateTablePath = "";
        //创建分库的唯一标识
        String primary = baseDao.getPrimary(m);
        //获取到了唯一标识，创建数据库文件
        if (!TextUtils.isEmpty(primary)) {
            File file = new File(def_db_path);
            boolean iscreate = true;
            //不存在默认的数据库则创建
            if (!file.exists()) {
                //创建成功
                if (!file.mkdirs()) {
                    iscreate = false;
                }
            }
            if (iscreate) {
                //如果数据库根目录存在，则返回分库路径
                //这样，每一个表都有一个单独的数据库。例如，用户表，根据用户id将每个用户分到独立的数据库中，这个数据库中只存在当前用户的一些列表信息
                separateTablePath = file.getAbsolutePath() + "/" + baseDao.getTableName() + "_" + primary + ".db";
            }
        }
        return separateTablePath;
    }

    //创建当前用户的分数据库的备份路径
    public <M> String getSeparateTableBackupPath(BaseDao<M> baseDao, M m) {
        String separateTablePath = "";
        //创建分库的唯一标识
        String primary = baseDao.getPrimary(m);
        //获取到了唯一标识，创建数据库文件
        if (!TextUtils.isEmpty(primary)) {
            File file = new File(def_db_path);
            boolean iscreate = true;
            //不存在默认的数据库则创建
            if (!file.exists()) {
                //创建成功
                if (!file.mkdirs()) {
                    iscreate = false;
                }
            }
            if (iscreate) {
                //如果数据库根目录存在，则返回分库路径
                separateTablePath = file.getAbsolutePath() + "/" + baseDao.getTableName() + "_" + primary + "_" + 1 + "_backup.db";
            }
        }
        return separateTablePath;
    }
}

