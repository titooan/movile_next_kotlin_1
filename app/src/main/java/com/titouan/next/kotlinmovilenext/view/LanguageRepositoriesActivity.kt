package com.titouan.next.kotlinmovilenext.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.titouan.next.kotlinmovilenext.R
import com.titouan.next.kotlinmovilenext.adapter.RepositoryAdapter
import com.titouan.next.kotlinmovilenext.api.GithubRepositoriesResult
import com.titouan.next.kotlinmovilenext.api.RepositoriesRetriever
import kotlinx.android.synthetic.main.activity_language_repositories.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LanguageRepositoriesActivity : AppCompatActivity() {

    companion object {
        const val LANGUAGE_NAME = "extra_language_name"

        fun getStartIntent(context: Context, language: String) : Intent {
            val intent = Intent(context, LanguageRepositoriesActivity::class.java)
            intent.putExtra(LANGUAGE_NAME, language)
            return intent
        }
    }

    private val repositoryRetriever = RepositoriesRetriever()

    private val callback = object : Callback<GithubRepositoriesResult> {

        override fun onFailure(call: Call<GithubRepositoriesResult>?, t: Throwable?) {
            toast("Repositories request returned with failure")
            progressBar.visibility = View.GONE
        }

        override fun onResponse(call: Call<GithubRepositoriesResult>?, response: Response<GithubRepositoriesResult>?) {
            toast("Request returned with success")

            progressBar.visibility = View.GONE

            response?.isSuccessful.let {
                response?.body()?.repositories?.let {
                    recyclerView.adapter =
                            RepositoryAdapter(it, this@LanguageRepositoriesActivity) {
                                toast(it.full_name ?: "Unknown")
                            }
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_repositories)

        intent.getStringExtra(LANGUAGE_NAME)?.let {
            repositoryRetriever.getLanguageRepositories(callback, it)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}
