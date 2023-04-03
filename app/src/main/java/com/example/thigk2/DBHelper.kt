package com.example.thigk2
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context, "Contact", null, 1) {
    //context, "Userdata", null, 1


    fun getAllUser(): ArrayList<OutData> {
        val userList = ArrayList<OutData>()
        val db = this.writableDatabase
        // Select All Query
        val selectQuery = "SELECT * FROM Contact"
        db.close()
        return userList
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Contact(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, email TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists Userdata")

    }
    fun savedata (name: String,phone: String, email: String  ): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("phone", phone)
        cv.put("email", email)
        val result = p0.insert("Contact",null, cv)
        if(result==-1 .toLong()){
            return false
        }
        return true
    }

    fun gettext(): Cursor?{
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select*from Contact", null)
        return  cursor

    }

    fun deleteData(name: String): Int {
        val db = this.writableDatabase
        val ersult = db!!.delete("Contact", "name=?", arrayOf(name.toString()))
        db.close()
        return ersult
    }
    fun update(id: String, name: String, phone: String, email: String): Int{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("phone", phone)
        cv.put("email", email)
        val count = db!!.update("Contact", cv, "id=?", arrayOf(id.toString()))
        db.close()
        return count

    }




}