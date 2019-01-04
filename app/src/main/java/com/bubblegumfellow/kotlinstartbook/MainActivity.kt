package com.bubblegumfellow.kotlinstartbook

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bubblegumfellow.kotlinstartbook.model.Article
import com.bubblegumfellow.kotlinstartbook.model.User
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

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

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://qiita.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val articleClient = retrofit.create(ArticleClient::class.java)

        searchButton.setOnClickListener { _ ->
            disposable = articleClient.search(queryEditText.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ articles ->
                    queryEditText.text.clear()
                    listAdapter.articles = articles
                    listAdapter.notifyDataSetChanged()
                }, {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_LONG).show()
                })
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable?.dispose()
        disposable = null
    }

    // ダミー記事を生成するメソッド
    private fun dummyArticle(title: String, userName: String): Article {
        return Article(id = "", title = title, url = "https://kotlinlang.org/", user = User(id = "", name = userName, profileImageUrl = ""))
    }
}
