package com.bubblegumfellow.kotlinstartbook

import com.bubblegumfellow.kotlinstartbook.model.Article
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleClient {
    @GET("/api/v2/items")
    fun search(@Query("query") query: String): Observable<List<Article>>

}