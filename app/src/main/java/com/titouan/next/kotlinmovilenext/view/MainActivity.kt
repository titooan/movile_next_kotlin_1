package com.titouan.next.kotlinmovilenext.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.titouan.next.kotlinmovilenext.R
import com.titouan.next.kotlinmovilenext.adapter.ProgrammingLanguageAdapter
import com.titouan.next.kotlinmovilenext.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = ProgrammingLanguageAdapter(
                recyclerViewItems(), this) {
            longToast("Clicked item: ${it.title}")
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun recyclerViewItems(): List<ProgrammingLanguage> =
        listOf(
                ProgrammingLanguage(R.drawable.kotlin, "Kotlin", 2010, R.string.kotlin_description),
                ProgrammingLanguage(0, "Java", 1995, R.string.java_description),
                ProgrammingLanguage(0, "Python", 1991, R.string.python_description))

}
