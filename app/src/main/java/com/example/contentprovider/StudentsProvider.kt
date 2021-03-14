package com.example.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class StudentsProvider:ContentProvider() {
    lateinit var db:SQLiteDatabase

    companion object{
        val PROVIDER_NAME = "com.example.contentprovider/StudentsProvider"
        val URL = "content://$PROVIDER_NAME/DATA"
        val CONTENT_URI = Uri.parse(URL)
        val _ID = "_id"
        val NAME = "NAME"
        val MEANING = "MEANING"
    }

    override fun onCreate(): Boolean
    {
        var helper = MyDataBase(context!!.applicationContext)
         db = helper.writableDatabase
        return if (db==null) false else true
    }



    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        db.insert("DATA",null,values)
        context?.contentResolver?.notifyChange(uri,null)
        return uri
    }
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {

        var count = db.update("DATA",values,selection,selectionArgs)
        context?.contentResolver?.notifyChange(uri,null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {

        var count  = db.delete("DATA",selection,selectionArgs)
        context?.contentResolver?.notifyChange(uri,null)
        return count
    }


    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return db.query("DATA",projection,selection,selectionArgs,null,null,sortOrder)
    }

    override fun getType(uri: Uri): String? {
     return "vnd.android.cursor.drr/vdn.example.data"

    }
}