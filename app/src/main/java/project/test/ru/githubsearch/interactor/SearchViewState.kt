package project.test.ru.githubsearch.interactor

import project.test.ru.githubsearch.pojo.User


interface SearchViewState {
    /**
     * The search has not been stared yet
     */
    class SearchNotStartedYet : SearchViewState

    class Loading : SearchViewState

    /**
     * Indicates that the search has delivered an empty result set
     */
    class EmptyResult(val searchQueryText: String) : SearchViewState {

        override fun toString(): String {
            return "EmptyResult{" +
                    "searchQueryText='" + searchQueryText + '\'' +
                    '}'
        }
    }

    /**
     * A valid search result. Contains a list of items that have matched the searching criteria.
     */
    class SearchResult(val searchQueryText: String, val result: List<User>) : SearchViewState {

        override fun toString(): String {
            return "SearchResult{" +
                    "searchQueryText='" + searchQueryText + '\'' +
                    ", result=" + result +
                    '}'
        }
    }

    /**
     * Says that an error has occurred while searching
     */
    class Error(val searchQueryText: String, val error: Throwable) : SearchViewState {

        override fun toString(): String {
            return "Error{" +
                    "searchQueryText='" + searchQueryText + '\'' +
                    ", error=" + error +
                    '}'
        }
    }
}