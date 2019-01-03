package com.bubblegumfellow.kotlinstartbook

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bubblegumfellow.kotlinstartbook.model.Article
import com.bubblegumfellow.kotlinstartbook.view.ArticleView

class ArticleListAdapter(private val context: Context) : BaseAdapter() {

    var articles: List<Article> = emptyList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return ((convertView as? ArticleView) ?: ArticleView(context)).apply {
            setArticle(articles[position])
        }
    }

    override fun getItem(position: Int): Any = articles[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = articles.size
}