package com.example.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String NAME = "todoListDB";
    public static final String TODO_TABLE= "todo";
    public static final String ID= "id";
    public static final String TASK= "task";
    public static final String STATUS= "status";
    public static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK + " TEXT, "
            + STATUS + " INTEGER)";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    //tao bang du lieu
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop bang? cu~
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        //tao lai bang moi
        onCreate(db);
    }

    public void openDb(){
        db = this.getWritableDatabase();
    }

    //them cong viec
    public void insertTask(TaskModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        db.insert(TODO_TABLE, null, cv);
    }

    //get cac task
    public List<TaskModel> getAllTask(){
        List<TaskModel> taskList = new ArrayList<>();
        Cursor cursor = null;
        db.beginTransaction();
        try{
            cursor = db.query(TODO_TABLE, null, null, null, null, null, null, null );
            if(cursor != null){
                if( cursor.moveToFirst()){
                    TaskModel task = new TaskModel();
                    task.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                    task.setTask(cursor.getString(cursor.getColumnIndex(TASK)));
                    task.setStatus(cursor.getInt(cursor.getColumnIndex(STATUS)));

                    taskList.add(task);
                }while(cursor.moveToNext());
            }
        }
        finally {
            db.endTransaction();
            assert cursor != null;
            cursor.close();
        }
        return  taskList;
    }


    //update trang thai check hay chua
    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    //update cong viec
    public void updateTask(int id, String task){
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    //xoa cong viec
    public void deleteTask(int id){
        db.delete(TODO_TABLE,ID + "= ?", new String[] {String.valueOf(id)});
    }


}
