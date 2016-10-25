package iet.jxufe.cn.android.btest05;

/**
 * Created by lu on 2016/10/24.
 */
public class UserDBSchema {
    /**
     * 数据库文件名
     */
    public static final String DB_NAME="users.db";

    public static final class TABLE{
        /**
         * 表名
         */
        public static final String NAME="user_tb";

        public static final class COL{
            /**
             * 列名
             */
            public static final String NAME="name";
            public static final String PASSWORD="psd";
            public static final String GENDER="gender";
        }
    }

}
