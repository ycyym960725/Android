package iet.jxufe.cn.android.btest05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import iet.jxufe.cn.android.btest05.UserDBSchema.TABLE;

/**
 * Created by lu on 2016/10/24.
 */
public class UserManager {

    private static UserManager userManager;

    private Context context;
    private UserDBHelper userDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    private UserManager(Context context) {
        if (userDBHelper==null&&context!=null){
            this.context = context;
            userDBHelper=new UserDBHelper(context.getApplicationContext());
            sqLiteDatabase=userDBHelper.getWritableDatabase();
            /**
             * 关闭之后重新打开会报异常
             */
        }
    }


    public static UserManager getUserManager(Context context){
        if (userManager==null){
            userManager=new UserManager(context);
        }
        return userManager;
    }

    /**
     * @param user 用户，需要所有属性都存在
     * @return ContentValues
     */
    public static ContentValues getContentValues(User user){
        ContentValues contentValues=new ContentValues();
        contentValues.put(TABLE.COL.NAME,user.getName());
        contentValues.put(TABLE.COL.PASSWORD,user.getPassword());
        contentValues.put(TABLE.COL.GENDER,user.getGender());
        return contentValues;
    }

    /**
     * 用户三个参数必须存在
     * 保存到SQLite中，成功保存返回true，否则false
     */
    public boolean saveToSQLite(User user) {
        boolean flag=false;
        if (!isInSQLite(user.getName())){
            ContentValues contentValues=getContentValues(user);
            /**
             *  插入方法不会考虑重复元素
             */
            sqLiteDatabase.insert(UserDBSchema.TABLE.NAME,null,contentValues);
            flag=true;
        }
        return flag;
    }


    /**
     * 存在user为真，不存在为假
     * 只校验用户名 for register
     * @return
     */
    public boolean isInSQLite(String userName) {
        boolean flag=false;
        Cursor cursor=sqLiteDatabase.query(TABLE.NAME,null, TABLE.COL.NAME+" = ? ",new String[]{ userName },null,null,null);
        /**
         *  query不到也不会返回空的cursor
         */
        int name_col_index=cursor.getColumnIndex(TABLE.COL.NAME);
        /**
         * moveToNext ()
         * Move the cursor to the next row.
         * This method will return false if the cursor is already past the last entry in the result set.
         */
        if (cursor.moveToFirst())
        {
            String name=cursor.getString(name_col_index);
            if (userName.equals(name)){
                flag=true;
            }
        }
        cursor.close();
        return flag;
    }


    /**
     * 检验用户名和密码，存在则真，不存在为假
     * for login
     * @param user
     * @return
     */
    public boolean isInSQLite(User user){
        boolean flag=false;
        Cursor cursor=sqLiteDatabase.query(TABLE.NAME,null, TABLE.COL.NAME+" = ? ",new String[]{ user.getName() },null,null,null);
        /**
         *  query不到也不会返回空的cursor
         */
        int name_col_index=cursor.getColumnIndex(TABLE.COL.NAME);
        int password_col_index=cursor.getColumnIndex(TABLE.COL.PASSWORD);
        /**
         * moveToNext ()
         * Move the cursor to the next row.
         * This method will return false if the cursor is already past the last entry in the result set.
         */
        if (cursor.moveToFirst())
        {
            int i=0;
            String name=cursor.getString(name_col_index);
            String password=cursor.getString(password_col_index);
            if (user.getName().equals(name)){
                i++;
            }
            if (user.getPassword().equals(password))
                i++;
            if (i==2){
                flag=true;
            }
        }
        cursor.close();
        return flag;
    }
}
