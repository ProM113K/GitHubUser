package com.example.githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubUserViewModel : ViewModel() {

    private val searchUserItem = MutableLiveData<ArrayList<ItemsItem>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        setSearchUsers("Dicoding")
    }

    fun setSearchUsers(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserSearch(query)
        client.enqueue(object : Callback<GithubUserResponse> {
            override fun onResponse(
                call: Call<GithubUserResponse>,
                response: Response<GithubUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        searchUserItem.postValue(response.body()?.items)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getSearchUsers(): LiveData<ArrayList<ItemsItem>> {
        return searchUserItem
    }

    companion object {
        private const val TAG = "GithubUserViewModel"
    }
}