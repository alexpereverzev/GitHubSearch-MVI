package project.test.ru.githubsearch.api

import io.reactivex.Observable
import project.test.ru.githubsearch.pojo.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("/search/users")
    fun getUsers(@Query("q") search: String): Observable<SearchResponse>
}