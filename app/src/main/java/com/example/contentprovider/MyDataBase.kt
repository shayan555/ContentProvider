package com.example.contentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDataBase( context: Context):SQLiteOpenHelper(context,"MyDb",null,1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE DATA(_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,MEANING TEXT)")
        db?.execSQL("INSERT INTO  DATA(NAME,MEANING) VALUES('MCA','Master of Computer Application')")
        db?.execSQL("INSERT INTO  DATA(NAME,MEANING) VALUES('BCA','Bachlor of Computer Application')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}