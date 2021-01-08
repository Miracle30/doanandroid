package com.example.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.model.Notes;

import java.util.ArrayList;
import java.util.List;

public class NoteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Note.db";
    private static final String TABLE_NAME = "notes";
    private static final int SCHEMA_VERSION= 1;
//    private static final String COL_1 = "ID";
//    private static final String COL_2 = "TASK";
//    private static final String COL_3 = "STATUS";


    public NoteHelper(Context context ) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    public NoteHelper(@Nullable Context context, @Nullable String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , note TEXT , content TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //insert vao bang
    public void insertNote(Notes model){
        ContentValues cv = new ContentValues();
        cv.put("note" , model.getNote());
        cv.put("content" , model.getContent());
        getWritableDatabase().insert(TABLE_NAME,null,cv);
    }
    //truy van toan bo
    public Cursor getAll(){
        Cursor cursor;
        cursor = getReadableDatabase().rawQuery("SELECT ID, note, content FROM "+ TABLE_NAME + " ORDER BY note", null);
        return  cursor;
    }

    public String getNote(Cursor c)
    {
        // truy cap cot thu 2 la cot title note
        return (c.getString(1));
    }
    public String getContent(Cursor c)
    {
        // truy cap cot thu 3 la cot content
        return (c.getString(2));
    }


//
//    //update cong viec
//    public void updateTask(int id , String task){
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_2 , task);
//        db.update(TABLE_NAME , values , "ID=?" , new String[]{String.valueOf(id)});
//    }
//
//    //update trang thai
//    public void updateStatus(int id , int status){
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_3 , status);
//        db.update(TABLE_NAME , values , "ID=?" , new String[]{String.valueOf(id)});
//    }
//
//    //xoa cong viec
//    public void deleteTask(int id ){
//        db = this.getWritableDatabase();
//        db.delete(TABLE_NAME , "ID=?" , new String[]{String.valueOf(id)});
//    }
//
//    //get all task
//    public List<TaskModel> getAllTasks(){
//
//        db = this.getWritableDatabase();
//        Cursor cursor = null;
//        List<TaskModel> modelList = new ArrayList<>();
//
//        db.beginTransaction();
//        try {
//            cursor = db.query(TABLE_NAME , null , null , null , null , null , null);
//            if (cursor !=null){
//                if (cursor.moveToFirst()){
//                    do {
//                        TaskModel task = new TaskModel();
//                        task.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
//                        task.setTask(cursor.getString(cursor.getColumnIndex(COL_2)));
//                        task.setStatus(cursor.getInt(cursor.getColumnIndex(COL_3)));
//                        modelList.add(task);
//
//                    }while (cursor.moveToNext());
//                }
//            }
//        }finally {
//            db.endTransaction();
//            cursor.close();
//        }
//        return modelList;
//    }


}