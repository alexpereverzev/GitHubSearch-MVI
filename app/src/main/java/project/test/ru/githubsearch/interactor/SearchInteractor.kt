package project.test.ru.githubsearch.interactor

import io.reactivex.Observable
import project.test.ru.githubsearch.repository.SearchRepository

class SearchInteractor(val searchRepository: SearchRepository) {

    fun search(searchString: String): Observable<SearchViewState> {
        // Empty String, so no search
        return if (searchString.isEmpty()) {
            Observable.just(SearchViewState.SearchNotStartedYet())
        } else searchRepository.searchFor(searchString).map { users ->
            if (users.isEmpty()) {
                return@map SearchViewState.EmptyResult(searchString)
            } else {
                return@map SearchViewState.SearchResult(searchString, users)
            }
        }
                .startWith(SearchViewState.Loading())
                .onErrorReturn { error -> SearchViewState.Error(searchString, error) }
    }

}