package com.example.contentprovider

import android.content.ContentValues
import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var helper = MyDataBase(this)
//        var db = helper.readableDatabase
//        var rs = db.rawQuery("SELECT * FROM DATA",null)
//        if (rs.moveToFirst())
//        {
//            Toast.makeText(this,rs.getString(1)+"\n"+rs.getString(2),Toast.LENGTH_LONG).show()
//        }

        var rs = contentResolver.query(StudentsProvider.CONTENT_URI, arrayOf(StudentsProvider._ID,StudentsProvider.NAME,StudentsProvider.MEANING),null,null)

        next_button.setOnClickListener()
        {
            if (rs?.moveToNext()!!)
            {
                name_edittext.setText(rs.getString(1))
                meaning_edittext.setText(rs.getString(2))
            }
        }

        previous_button.setOnClickListener()
        {
            if (rs?.moveToPrevious()!!)
            {
                name_edittext.setText(rs.getString(1))
                meaning_edittext.setText(rs.getString(2))
            }
        }
        insert_button.setOnClickListener()
        {
            var values = ContentValues()
            values.put(StudentsProvider.NAME,name_edittext.text.toString())
            values.put(StudentsProvider.MEANING,meaning_edittext.text.toString())
            contentResolver.insert(StudentsProvider.CONTENT_URI,values)
            rs?.requery()

        }
        clear_button.setOnClickListener()
        {
            name_edittext.setText("")
            meaning_edittext.setText("")
            name_edittext.requestFocus()
        }
        update_button.setOnClickListener()
        {
            var values = ContentValues()
            values.put(StudentsProvider.MEANING, meaning_edittext.text.toString() )
            contentResolver.update(StudentsProvider.CONTENT_URI,values,"NAME = ?", arrayOf(name_edittext.text.toString()))
            rs?.requery()
        }

        delete_button.setOnClickListener()
        {
            contentResolver.delete(StudentsProvider.CONTENT_URI,"NAME = ?", arrayOf(name_edittext.text.toString()))
            rs?.requery()
        }













    }
}