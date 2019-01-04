package com.bubblegumfellow.kotlinstartbook

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bubblegumfellow.kotlinstartbook.model.Article
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    companion object {
        private const val ARTICLE_EXTRA: String = "article"

        fun intent(context: Context, article: Article): Intent {
            return Intent(context, ArticleActivity::class.java).apply {
                putExtra(ARTICLE_EXTRA, article)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val article: Article = intent.getParcelableExtra(ARTICLE_EXTRA)
        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }
}
