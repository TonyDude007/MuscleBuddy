package com.example.musclebuddy.service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.musclebuddy.helper.MuscleBuddyDbHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDb {
    private static int version = 1;
    private static String dbName = "MuscleBuddy.db";
    private static SQLiteDatabase db = null;
    private static MuscleBuddyDbHelper helper;

    public static SQLiteDatabase getDb(Context context){
        if(helper == null){
            helper = new MuscleBuddyDbHelper(context, dbName, null, version);
        }
        db = helper.getWritableDatabase();
        return db;
    }
    public static void close(){
        if(db!= null &&  !db.isOpen()){
            db.close();
        }
    }
}
