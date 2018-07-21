package com.titouan.next.kotlinmovilenext.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.titouan.next.kotlinmovilenext.R
import com.titouan.next.kotlinmovilenext.adapter.ProgrammingLanguageAdapter
import com.titouan.next.kotlinmovilenext.adapter.RepositoryAdapter
import com.titouan.next.kotlinmovilenext.api.GithubRepositoriesResult
import com.titouan.next.kotlinmovilenext.api.RepositoriesRetriever
import com.titouan.next.kotlinmovilenext.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val repositoryRetriever = RepositoriesRetriever()

    private val callback = object : Callback<GithubRepositoriesResult> {

        override fun onFailure(call: Call<GithubRepositoriesResult>?, t: Throwable?) {
            toast("Repositories request returned with failure")
        }

        override fun onResponse(call: Call<GithubRepositoriesResult>?, response: Response<GithubRepositoriesResult>?) {
            toast("Request returned with success")

            response?.isSuccessful.let {
                response?.body()?.repositories?.let {
                    recyclerView.adapter =
                            RepositoryAdapter(it, this@MainActivity) {
                                toast(it.full_name ?: "Unknown")
                            }
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        recyclerView.adapter = ProgrammingLanguageAdapter(
                recyclerViewItems(), this) {
            longToast("Clicked item: ${it.title}")
            repositoryRetriever.getLanguageRepositories(callback, it.title)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun recyclerViewItems(): List<ProgrammingLanguage> =
        listOf(
                ProgrammingLanguage(R.drawable.kotlin, "Kotlin", 2010, R.string.kotlin_description),
                ProgrammingLanguage(0, "Java", 1995, R.string.java_description),
                ProgrammingLanguage(0, "Python", 1991, R.string.python_description))

}
