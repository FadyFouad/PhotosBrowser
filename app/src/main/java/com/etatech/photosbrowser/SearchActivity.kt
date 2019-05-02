package com.etatech.photosbrowser

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView

class SearchActivity : BaseActivity() {
    var searchView : SearchView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolBar(true)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)
        val searchManger = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val searchInfo = searchManger.getSearchableInfo(componentName)
        searchView?.isIconified = false
        searchView?.setSearchableInfo(searchInfo)
        searchView?.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val sharPref=PreferenceManager.getDefaultSharedPreferences(applicationContext)
                sharPref
                    .edit()
                    .putString(FLICKR_QUERY,query)
                    .apply()
                searchView?.clearFocus()
                finish()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        searchView?.setOnCloseListener {
            finish()
            false
        }
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return super.onOptionsItemSelected(item)
    }
}
