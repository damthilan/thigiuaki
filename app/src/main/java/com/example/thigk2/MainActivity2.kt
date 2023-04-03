package com.example.thigk2

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem

class MainActivity2 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dhb: DBHelper
    private lateinit var newArry: ArrayList<OutData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerView = findViewById(R.id.recycler)
        dhb = DBHelper(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        displayuser()

//        dhb.deleteData()
//
    }
    private fun displayuser() {
        var newcursor: Cursor? = dhb!!.gettext()
        newArry = ArrayList<OutData>()
        while (newcursor!!.moveToNext()){
            var id = newcursor.getString(0)
            var name = newcursor.getString(1)
            var track = newcursor.getString(2)
            var duration = newcursor.getString(3)
            newArry.add(OutData(id , name, track, duration))
        }
        recyclerView.adapter = MyAdapter( this, newArry)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.show1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itmEdit -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return (super.onOptionsItemSelected(item))
    }



}