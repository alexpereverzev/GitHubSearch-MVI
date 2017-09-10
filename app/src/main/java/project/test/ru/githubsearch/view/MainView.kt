package project.test.ru.githubsearch.view

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable
import project.test.ru.githubsearch.interactor.SearchViewState


interface MainView : MvpView {

    /**
     * The search intent
     *
     * @return An observable emitting search query text
     */
    fun searchIntent(): Observable<String>
    /**
     * Renders the viewState
     *
     * @param viewState The current viewState state that should be displayed
     */
    fun render(viewState: SearchViewState)
}