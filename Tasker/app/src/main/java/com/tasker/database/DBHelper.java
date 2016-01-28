package com.tasker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.tasker.model.ModelTask;

/**
 * Created by FVolodia on 28.01.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tasker_database";
    public static final String TASK_TABLE = "task_table";

    public static final String TASK_TITLE_COLUMN = "task_title";
    public static final String TASK_DATE_COLUMN = "task_date";
    public static final String TASK_PRIORITY_COLUMN = "task_priority";
    public static final String TASK_STATUS_COLUMN = "task_status";
    public static final String TASK_TIME_STAMP_COLUMN = "task_time_stamp";

    private static final String TASK_TABLE_CREATE_SCRIPT = "CREATE TABLE "
            + TASK_TABLE + " (" + BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_TITLE_COLUMN + " TEXT NOT NULL, "
            + TASK_DATE_COLUMN + " LONG, " + TASK_PRIORITY_COLUMN + " INTEGER, "
            + TASK_STATUS_COLUMN + " INTEGER, " + TASK_TIME_STAMP_COLUMN + " LONG);";

    public static final String SELECTION_STATUS = DBHelper.TASK_STATUS_COLUMN + " = ?";

    private DBQueryManager dbQueryManager;
    private DBUpdateManger dbUpdateManger;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbQueryManager = new DBQueryManager(getReadableDatabase());
        dbUpdateManger = new DBUpdateManger(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_TABLE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TASK_TABLE);
        onCreate(db);
    }

    public void saveTask(ModelTask task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_TITLE_COLUMN, task.getTitle());
        contentValues.put(TASK_DATE_COLUMN, task.getDate());
        contentValues.put(TASK_PRIORITY_COLUMN, task.getPriority());
        contentValues.put(TASK_STATUS_COLUMN, task.getStatus());
        contentValues.put(TASK_TIME_STAMP_COLUMN, task.getTimeStamp());

        getWritableDatabase().insert(TASK_TABLE, null, contentValues);
    }

    public DBQueryManager query(){
        return dbQueryManager;
    }

    public DBUpdateManger update(){
        return dbUpdateManger;
    }
}
