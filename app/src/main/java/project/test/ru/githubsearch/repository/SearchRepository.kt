package project.test.ru.githubsearch.repository

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import project.test.ru.githubsearch.api.Api
import project.test.ru.githubsearch.pojo.User

class SearchRepository(val api: Api)
{
    fun searchFor(searchQueryText: String): Observable<List<User>> {

        return if (searchQueryText.isBlank()) {
            Observable.error<List<User>>(IllegalArgumentException("SearchQuery is blank"))
        } else api.getUsers(searchQueryText)
                .subscribeOn(Schedulers.io())
                .flatMap(({ Observable.just(it.items)}))
    }
}