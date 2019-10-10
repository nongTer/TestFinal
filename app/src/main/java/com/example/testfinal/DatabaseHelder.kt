package com.example.testfinal

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.FontsContract

class DatabaseHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,1) {

    companion object {
        val DATABASE_NAME = "Student.db"
        val TABLE_NAME = "student_table"
        val Column1 = "ID"
        val Column2 = "NAME"
        val Column3 = "LAST_NAME"
        val Column4 = "STUDENT_NUMBER"

        }



        override fun onCreate(db: SQLiteDatabase) {

            db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME  TEXT , PROFESSION TEXT , SALARY INTEGER)")

        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)

        }


        fun intertData(name: String, lastname: String, student: String): Boolean? {
            val db = this.writableDatabase
            val cv = ContentValues()
            cv.put(Column2, name)
            cv.put(Column3, lastname)
            cv.put(Column4, student)
            val res = db.insert(TABLE_NAME , null, cv)
            return !res.equals(-1)
        }


        fun getAllData(): Cursor {

            val db = this.writableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        }


        fun getData(id: String): Cursor {
            val db = this.writableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID=? ", arrayOf(id), null)
        }



        fun daleteData(id: String): Int? {
            val db = this.writableDatabase
            return db.delete(TABLE_NAME, "ID =? ", arrayOf(id))
        }


    }
