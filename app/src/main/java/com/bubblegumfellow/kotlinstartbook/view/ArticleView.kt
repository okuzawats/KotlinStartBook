package com.bubblegumfellow.kotlinstartbook.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import com.bubblegumfellow.kotlinstartbook.model.Article
import kotlinx.android.synthetic.main.view_article.view.*

class ArticleView : FrameLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun setArticle(article: Article) {
        titleTextView.text = article.title
        userNameTextView.text = article.user.name

        // TODO：プロフィール画像をセットする
        profileImageView.setBackgroundColor(Color.RED)
    }
}