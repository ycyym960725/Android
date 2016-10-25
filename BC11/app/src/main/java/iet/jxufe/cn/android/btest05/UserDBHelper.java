package iet.jxufe.cn.android.btest05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import iet.jxufe.cn.android.btest05.UserDBSchema.TABLE;

import static iet.jxufe.cn.android.btest05.UserDBSchema.DB_NAME;

/**
 * Created by lu on 2016/10/24.
 * A helper class to manage database creation and version management.
 */
public class UserDBHelper extends SQLiteOpenHelper {
    /**
     * 数据库版本
     */
    private static final int VERSION=1;

    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        /**
         * SQLiteOpenHelper (Context context,String database_name,SQLiteDatabase.CursorFactory factory,int version)
         */
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /**
         *  sql语句中需要注意空格
         */
        String sql="create table if not exists " + TABLE.NAME+"("+" _id integer primary key autoincrement, "+
                TABLE.COL.NAME+", "+TABLE.COL.PASSWORD+", "+TABLE.COL.GENDER+")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE.NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
