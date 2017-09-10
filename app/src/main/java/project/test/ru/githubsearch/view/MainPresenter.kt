package project.test.ru.githubsearch.view

import android.util.Log
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import project.test.ru.githubsearch.interactor.SearchInteractor
import project.test.ru.githubsearch.interactor.SearchViewState


class MainPresenter(val searchInteractor: SearchInteractor) : MviBasePresenter<MainView, SearchViewState>(SearchViewState.SearchNotStartedYet()) {

    override fun bindIntents() {
        val search = intent(MainView::searchIntent)
                .doOnNext { s -> Log.d("intent: Search '%s'", s) }
                .switchMap { s -> searchInteractor.search(s) }
                .observeOn(AndroidSchedulers.mainThread())
        subscribeViewState(search, MainView::render)
    }

}