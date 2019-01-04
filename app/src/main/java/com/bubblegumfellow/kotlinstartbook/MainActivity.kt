package com.bubblegumfellow.kotlinstartbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bubblegumfellow.kotlinstartbook.model.Article
import com.bubblegumfellow.kotlinstartbook.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext).apply {
            articles = listOf(
                dummyArticle("Kotlin入門", "たろう"),
                dummyArticle("Java入門", "じろう")
            )
        }
        listView.apply {
            adapter = listAdapter
            setOnItemClickListener { adapterView, view, position, id ->
                val article = listAdapter.articles[position]
                ArticleActivity.intent(context, article).let {
                    startActivity(it)
                }
            }
        }

    }

    // ダミー記事を生成するメソッド
    private fun dummyArticle(title: String, userName: String): Article {
        return Article(id = "", title = title, url = "https://kotlinlang.org/", user = User(id = "", name = userName, profileImageUrl = ""))
    }
}
