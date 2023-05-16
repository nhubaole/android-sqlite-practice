package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ClassRepo extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "class.db";

    public static final String SQL_CREATE_TABLE_CLASS = "CREATE TABLE CLASS(ID VARCHAR(10) PRIMARY KEY, CLASSNAME VARCHAR(50))";
    public static final String SQL_CREATE_TABLE_STUDENT = "CREATE TABLE STUDENT(ID VARCHAR(10) PRIMARY KEY, STUDENTNAME VARCHAR(100), DOB VARCHAR(50), CLASSID VARCHAR(10), CONSTRAINT FK_STUDENT FOREIGN KEY (CLASSID) REFERENCES CLASS(ID))";
    public static final String SQL_DELETE_TABLE_CLASS = "DELETE CLASS";
    public static final String SQL_DELETE_TABLE_STUDENT = "DELETE STUDENT";

    public ClassRepo(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_CLASS);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE_CLASS);
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE_STUDENT);
        onCreate(sqLiteDatabase);
    }

    public void addClass(Class item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", item.getId());
        values.put("CLASSNAME", item.getName());
        db.insert("CLASS", null, values);
        db.close();
    }
    public void addStudent(Student item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", item.get_id());
        values.put("STUDENTNAME", item.get_name());
        values.put("DOB", item.get_dob());
        values.put("CLASSID", item.get_class());
        db.insert("STUDENT", null, values);
        db.close();
    }

    public ArrayList<Class> loadAllClass (){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
                "ID", "CLASSNAME"
        };
        Cursor cursor = db.query("CLASS", projection, null, null, null, null, null);
        ArrayList<Class> classes = new ArrayList<Class>();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String className = cursor.getString(1);
            classes.add(new Class(id, className));
        }
        db.close();
        return classes;
    }

    public ArrayList<Student> getStudentByClassID(String classID){
        ArrayList<Student> studentList = new ArrayList<Student>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = {
                "ID", "STUDENTNAME", "DOB", "CLASSID"
        };
        Cursor cursor = db.query("STUDENT", projection, "CLASSID = ?", new String[]{classID}, null, null, null);
        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String studentName = cursor.getString(1);
                String dob = cursor.getString(2);
                String classId = cursor.getString(3);
                studentList.add(new Student(id, studentName, dob, classId));
            } while (cursor.moveToNext());
        }
        db.close();
        return studentList;
    }

    public int countStudentsByClassID(String classID){
        ArrayList<Student> studentList = new ArrayList<Student>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] projection = { "COUNT(*)" };
        Cursor cursor = db.query("STUDENT", projection, "CLASSID = ?", new String[]{classID}, null, null, null);
        int count = 0;
        if(cursor.moveToFirst()){
           count = cursor.getInt(0);
        }
        db.close();
        return count;
    }

    public boolean deleteStudent(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int rowAffected = db.delete("STUDENT", "ID = ?", new String[]{id});
        db.close();
        return rowAffected > 0;
    }

}
