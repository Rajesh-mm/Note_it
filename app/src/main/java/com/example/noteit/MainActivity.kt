package com.example.noteit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.noteit.adapter.NoteAdapter
import com.example.noteit.db.DBOpenHelper
import com.example.noteit.model.NoteModel

class MainActivity : AppCompatActivity() {


    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var fabCreate: FloatingActionButton
    private lateinit var myDataset: MutableList<NoteModel>
    private val dbOpenHelper = DBOpenHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView = findViewById(R.id.main_recycler_view)
        fabCreate = findViewById(R.id.fab_create)

        myDataset = dbOpenHelper.readNotes()

        mainRecyclerView.adapter = NoteAdapter(this, myDataset)
        mainRecyclerView.setHasFixedSize(true)
        if(mainRecyclerView.adapter?.itemCount==0){
            var text: TextView = findViewById(R.id.emptydb)
            text.visibility = View.VISIBLE
        }
        else{
            var text: TextView = findViewById(R.id.emptydb)
            text.visibility = View.GONE
        }


        fabCreate.setOnClickListener {
            val intentToAddNoteActivity = Intent(this, AddNoteActivity::class.java)
            startActivity(intentToAddNoteActivity)
        }

    }
}