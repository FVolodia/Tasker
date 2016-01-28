package com.tasker.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tasker.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FVolodia on 28.01.16.
 */
public class DBQueryManager {
    private SQLiteDatabase database;

    public DBQueryManager(SQLiteDatabase database) {
        this.database = database;
    }

    public List<ModelTask> getTask(String selections, String[] selectionsArgs, String orderBy) {
        List<ModelTask> tasks = new ArrayList<>();

        Cursor c = database.query(DBHelper.TASK_TABLE, null, selections, selectionsArgs, null, null, orderBy);

        if (c.moveToFirst()) {
            do {
                String title = c.getString(c.getColumnIndex(DBHelper.TASK_TITLE_COLUMN));
                long date = c.getLong(c.getColumnIndex(DBHelper.TASK_DATE_COLUMN));
                int priority = c.getInt(c.getColumnIndex(DBHelper.TASK_PRIORITY_COLUMN));
                int status = c.getInt(c.getColumnIndex(DBHelper.TASK_STATUS_COLUMN));
                long timeStamp = c.getLong(c.getColumnIndex(DBHelper.TASK_TIME_STAMP_COLUMN));

                ModelTask modelTask = new ModelTask(title, date, priority, status, timeStamp);
                tasks.add(modelTask);

            } while (c.moveToNext());
        }
        c.close();
        return tasks;
    }

}
