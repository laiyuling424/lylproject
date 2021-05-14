package com.lyl.db.dbupdata;

import java.util.List;

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 5/14/21 9:14 AM
 */
public class UpdateDbBean {
    private CreateVersion createVersion;//需要升级的数据库

    public CreateVersion getCreateVersion() {
        return createVersion;
    }

    public void setCreateVersion(CreateVersion createVersion) {
        this.createVersion = createVersion;
    }

//    public UpdateStep getUpdateStep() {
//        return updateStep;
//    }
//
//    public void setUpdateStep(UpdateStep updateStep) {
//        this.updateStep = updateStep;
//    }

    //升级的数据库的版本和表
    public class CreateVersion {
        private long createVersion;//当前需要升级到的目标版本
        private List<CreateDb> createDbList;//需要升级的数据库表

        public long getCreateVersion() {
            return createVersion;
        }

        public void setCreateVersion(long createVersion) {
            this.createVersion = createVersion;
        }

        public List<CreateDb> getCreateDbList() {
            return createDbList;
        }

        public void setCreateDbList(List<CreateDb> createDbList) {
            this.createDbList = createDbList;
        }

        //需要升级的数据库的表名和执行sql
        public class CreateDb {
            private String name;//表名
            private List<String> createSqls;//创建表的sql,按顺序执行

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getCreateSqls() {
                return createSqls;
            }

            public void setCreateSqls(List<String> createSqls) {
                this.createSqls = createSqls;
            }
        }
    }
}

