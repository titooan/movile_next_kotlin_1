package com.titouan.next.kotlinmovilenext.api

import android.app.DownloadManager
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesRetriever {

    private val service: GithubService

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(GithubService::class.java)
    }

    fun getLanguageRepositories (
            callback: Callback<GithubRepositoriesResult>,
            query: String) {
        service.searchRepositories("language:$query").enqueue(callback)
    }

}