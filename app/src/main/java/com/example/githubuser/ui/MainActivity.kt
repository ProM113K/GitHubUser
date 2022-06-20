package com.example.githubuser.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.GithubUserViewModel
import com.example.githubuser.ItemsItem
import com.example.githubuser.UserAdapter
import com.example.githubuser.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GithubUserViewModel
    private lateinit var listUserAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this@MainActivity))
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GithubUserViewModel::class.java)

        showRecyclerGithubUser()

        viewModel.getSearchUsers().observe(this@MainActivity) {
            if (it != null) {
                listUserAdapter.setList(it)
            }
        }

        viewModel.isLoading.observe(this@MainActivity) {
            showLoading(it)
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showLoading(false)
                if (query != null) {
                    viewModel.setSearchUsers(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.isNotEmpty()) {
                        viewModel.setSearchUsers(newText)
                        listUserAdapter.notifyDataSetChanged()
                    } else {
                        showRecyclerGithubUser()
                        listUserAdapter.notifyDataSetChanged()
                    }
                }
                return true
            }
        })
    }

    private fun showRecyclerGithubUser() {
        binding.rvGithub.layoutManager = LinearLayoutManager(this@MainActivity)
        listUserAdapter = UserAdapter()
        binding.rvGithub.setHasFixedSize(true)
        listUserAdapter.notifyDataSetChanged()
        binding.rvGithub.adapter = listUserAdapter

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvGithub.layoutManager = GridLayoutManager(this@MainActivity, 2)
        } else {
            binding.rvGithub.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        listUserAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(data: ItemsItem) {
        val detail = Intent(this@MainActivity, UserDetailActivity::class.java)
        detail.putExtra(UserDetailActivity.EXTRA_DATA, data)
        startActivity(detail)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}