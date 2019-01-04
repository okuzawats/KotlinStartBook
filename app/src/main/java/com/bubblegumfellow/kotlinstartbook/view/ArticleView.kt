package com.bubblegumfellow.kotlinstartbook.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bubblegumfellow.kotlinstartbook.R
import com.bubblegumfellow.kotlinstartbook.bindView
import com.bubblegumfellow.kotlinstartbook.model.Article

class ArticleView : FrameLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    val titleTextView: TextView by bindView(R.id.titleTextView)
    val userNameTextView: TextView by bindView(R.id.userNameTextView)
    val profileImageView: ImageView by bindView(R.id.profileImageView)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)
    }

    fun setArticle(article: Article) {
        titleTextView.text = article.title
        userNameTextView.text = article.user.name

        // TODO：プロフィール画像をセットする
        profileImageView.setBackgroundColor(Color.RED)
    }
}