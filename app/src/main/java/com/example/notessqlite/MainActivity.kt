package com.example.notessqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notessqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //access layout file
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter //store data into a recycler view

    override fun onCreate(savedInstanceState: Bundle?) { //initial setup
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= NotesDatabaseHelper(this)
        notesAdapter= NotesAdapter(db.getAllNotes(), this)

        binding.notesRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.notesRecyclerView.adapter=notesAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for the logout button
        binding.logoutButton.setOnClickListener {
            // For example, navigate to LoginActivity
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish() // Finish MainActivity to prevent going back to it when pressing back button from LoginActivity
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes()) //retrieve updated data from the database
    }
}