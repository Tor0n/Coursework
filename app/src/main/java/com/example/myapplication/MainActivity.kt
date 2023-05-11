 package com.example.myapplication

import RcAdapter
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.database.MyDbManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.for_editing.EditActivity
import com.example.myapplication.network.AvailableEmployees
import kotlinx.coroutines.*

 class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    private val dbManager = MyDbManager(this)
    private val adapter = RcAdapter(ArrayList(), this)
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        init()
    }
    override fun onResume() {
        super.onResume()
        dbManager.openDB()
        fillAdapter("")
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }

    fun onClickAdd(view: View) {
        val i = Intent(this, EditActivity::class.java)
        startActivity(i)
    }

    private fun init() {
        binding.rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
        binding.rcView.adapter = adapter
    }

    //menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_for_main, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.search_view_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                fillAdapter(newText!!)
                return true
            }
        })
        return true
    }
     private fun fillAdapter(text: String) {
         job?.cancel()
         job = CoroutineScope(Dispatchers.Main).launch {
             if (dbManager.readDbData("").isEmpty()) binding.ifPresent.visibility = View.VISIBLE
             else binding.ifPresent.visibility = View.GONE
             adapter.updateAdapter(dbManager.readDbData(text))
         }
     }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.available_employees -> {
                val i = Intent(this, AvailableEmployees::class.java)
                startActivity(i)
            }
        }
        return true
    }

}