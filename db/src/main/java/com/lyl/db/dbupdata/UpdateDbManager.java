package com.lyl.db.dbupdata;

import android.database.sqlite.SQLiteDatabase;

import com.dds.dbframwork.sub_db.BaseDaoSubFactory;
import com.dds.dbframwork.sub_db.User;
import com.dds.dbframwork.sub_db.UserDao;

import java.util.List;

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 5/14/21 9:14 AM
 */
//从服务器拿到了更新数据库的数据后，解析成UpdateDbBean对象
public class UpdateDbManager {
    private UpdateDbBean updateDbBean;

    public void setUpdateDbBean(UpdateDbBean updateDbBean) {
        this.updateDbBean = updateDbBean;
    }

    //开始更新
    public void beginUpdateDb() {
        //得到当前数据库的版本号，这里写死1；实际情况应该是本地app保存的数据库版本
        long thisVersion = 1;
        //得到要升级的数据库信息
        UpdateDbBean.CreateVersion createVersion = updateDbBean.getCreateVersion();
        //要升级的版本号
        long fromVersion = createVersion.getCreateVersion();
        //执行升级
        if (fromVersion > thisVersion) {
            List<UpdateDbBean.CreateVersion.CreateDb> createDbs = createVersion.getCreateDbList();
            if (createDbs != null && !createDbs.isEmpty()) {
                UserDao userDao = BaseDaoSubFactory.getInstance().getBaseDao(UserDao.class, User.class);
                for (UpdateDbBean.CreateVersion.CreateDb createDb : createDbs) {
                    //这里涉及到多库、多表的情况。
                    //一条sql可能要在多个库和多张表中同时执行
                    //这里，模拟多库/多表的更新
                    //得到当前user所在的数据库的文件路径
                    List<User> users = userDao.query(new User());
                    if (users != null && !users.isEmpty()) {
                        for (User user : users) {
                            String tableName = createDb.getName();
                            //查询到每个user对应的表的sqlitedatabase对象，用于执行sql
                            SQLiteDatabase sqLiteDatabase = getSqlliteDataBase(tableName, userDao, user);
                            List<String> createSqls = createDb.getCreateSqls();
                            //开启事务，保证多条sql同时执行成功
                            sqLiteDatabase.beginTransaction();
                            try {
                                for (String createSql : createSqls) {
                                    sqLiteDatabase.execSQL(createSql);
                                }
                                //标记事务执行成功
                                sqLiteDatabase.setTransactionSuccessful();
                            } catch (Exception e) {
                                //执行失败则整个事务都不会提交
                                e.printStackTrace();
                            } finally {
                                //关闭事务
                                sqLiteDatabase.endTransaction();
                            }
                        }
                    }
                }
            }
        }
    }

    //通过userid查询到每个用户分库
    private SQLiteDatabase getSqlliteDataBase(String tableName, UserDao userDao, User user) {
        //得到当前user所在的数据库的文件路径
        String dbfilepath = BaseDaoSubFactory.getInstance().getSeparateTablePath(userDao, user);
        SQLiteDatabase sqLiteDatabase = null;
        //当前数据库是user的分库表，因为这个userdao是从basedao实例化出来的，所以，它不是分库数据库名，而是子库名字
        if ((tableName).equals(userDao.getTableName())) {
            //执行更新sql
            //先备份再更新
            //备份的规则是建立当前用户
            SQLiteDatabase sqLiteDatabaseBackup = SQLiteDatabase.openOrCreateDatabase(BaseDaoSubFactory.getInstance().getSeparateTableBackupPath(userDao, user), null);
            String sql = "";//这里是将当前User的信息保存起来的sql
            sqLiteDatabaseBackup.execSQL(sql);
            //创建数据库
            sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbfilepath, null);

        }
        return sqLiteDatabase;
    }
}

