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



}
