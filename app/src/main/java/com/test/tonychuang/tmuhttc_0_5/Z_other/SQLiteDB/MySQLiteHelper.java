package com.test.tonychuang.tmuhttc_0_5.Z_other.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * 創建不同使用者DB
 * 創建不同好友table
 * 刪除非好友table
 * 單筆寫入
 * Created by TonyChuang on 2016/3/17.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = null;    //資料庫名稱
    private static int DATABASE_VERSION = 1;    //資料庫版本

    private SQLiteDatabase db;
    private Context context;


    public MySQLiteHelper(Context context, String dbName, int dbVersion) { //dbName:使用登入者aid
        super(context, dbName, null, dbVersion);
        this.context = context;
        DATABASE_NAME = dbName;
        DATABASE_VERSION = dbVersion;
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /**
     * 新增表
     *
     * @param aid           會員aid
     * @param tableName     表種類名
     * @param columnNameAry 表欄位名array
     * @param columnTypeAry 表欄位格式array
     */
    public void createNewTable(String aid, String tableName, ArrayList<String> columnNameAry, ArrayList<String> columnTypeAry) {
        // 檢查資料表是否已經存在，如果不存在，就建立一個。
        Cursor cursor = null;
        String pTableName = aid + tableName;
        cursor = db.rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" +
                        pTableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() == 0) {                            // 沒有資料表，要建立一個資料表。
                String columnNameTypesString = initColumnNameTypes(columnNameAry, columnTypeAry);
                if (!columnNameTypesString.equals("")) {
                    db.execSQL("create table '" + pTableName + "' ("
                            + "_ID INTEGER PRIMARY KEY," + columnNameTypesString + ");");
                    cursor.close();
                } else {
                    //顯示沒有table欄位結構log
                }
            }
        }
    }


    /**
     * 刪除舊有的資料表
     *
     * @param aid       會員aid
     * @param tableName 表種類名
     */
    public void dropTable(String aid, String tableName) {
        String pTableName = aid + tableName;
        db.execSQL("DROP TABLE IF EXISTS '" + pTableName + "'");
        onCreate(db);
    }


    /**
     * 新增一筆記錄，成功回傳rowID，失敗回傳-1
     * @param aid           會員aid
     * @param tableName     表種類名
     * @param columnNameAry 表欄位名array
     * @param arrayList     欄位值arrayList
     * @return
     */
    public long create(String aid, String tableName, ArrayList<String> columnNameAry, ArrayList<String> arrayList) {
        String pTableName = aid + tableName;
        ContentValues args = new ContentValues();
        if (columnNameAry.size() == arrayList.size()) {
            for (int i = 0; i < arrayList.size(); i++) {
                args.put(columnNameAry.get(i), arrayList.get(i));
            }
        } else {
            //報錯處理
        }
        return db.insert("'" + pTableName + "'", null, args);
    }


    //用來初始化Table欄位與Type
    private String initColumnNameTypes(ArrayList<String> columnNameAry, ArrayList<String> columnTypeAry) {
        String columnNameTypesString = "";
        if (columnNameAry.size() == columnTypeAry.size()) {
            for (int i = 0; i < columnNameAry.size() - 1; i++) {
                columnNameTypesString = columnNameTypesString
                        + columnNameAry.get(i) + " " + columnTypeAry.get(i) + ",";
            }
            columnNameTypesString = columnNameTypesString
                    + columnNameAry.get(columnNameAry.size() - 1) + " "
                    + columnTypeAry.get(columnNameAry.size() - 1);
        } else {
            //顯示錯誤log
        }
        return columnNameTypesString;
    }
}
