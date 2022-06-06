package com.example.githubuser

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this@MainActivity))
        setContentView(binding.root)

        binding.rvGithub.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerGithubUser()
    }

    private val listUsers: ArrayList<User>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataAvatar = resources.getStringArray(R.array.avatar)

            val listUser = ArrayList<User>()

            for (i in dataName.indices) {
                val user = User(
                    dataName[i],
                    dataUsername[i],
                    dataLocation[i],
                    dataRepository[i],
                    dataAvatar[i]
                )
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerGithubUser() {
        binding.rvGithub.layoutManager = GridLayoutManager(this@MainActivity, 2)
        val listUserAdapter = UserAdapter(list)
        binding.rvGithub.adapter = listUserAdapter
    }
}