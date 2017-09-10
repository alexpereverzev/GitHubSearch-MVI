package project.test.ru.githubsearch.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.jakewharton.rxbinding2.widget.RxSearchView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import project.test.ru.githubsearch.App
import project.test.ru.githubsearch.R
import project.test.ru.githubsearch.interactor.SearchViewState
import project.test.ru.githubsearch.pojo.User
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : MviActivity<MainView, MainPresenter>(), MainView, SearchViewHolder.UserClickedListener {

    private lateinit var adapter: SearchAdapter
    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = SearchAdapter(LayoutInflater.from(this), this)
        rv_users.adapter = adapter
        rv_users.layoutManager = LinearLayoutManager(this)
        App.appComponent.inject(this)
    }

    override fun searchIntent(): Observable<String> {
        return RxSearchView.queryTextChanges(search)
                .skip(2) // Because after screen orientation changes query Text will be resubmitted again
                .filter { queryString -> queryString.length > 2 || queryString.isEmpty() }
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .map { it.toString() }
    }

    override fun render(viewState: SearchViewState) {
        Log.d("render ", viewState.toString())
        when (viewState) {
            is SearchViewState.SearchNotStartedYet -> renderSearchNotStarted()
            is SearchViewState.Loading -> renderLoading()
            is SearchViewState.SearchResult -> renderResult(viewState.result)
            is SearchViewState.EmptyResult -> renderEmptyResult()
            is SearchViewState.Error -> {
                Log.e("error", viewState.error.toString())
                renderError()
            }
            else -> throw IllegalArgumentException("Don't know how to render viewState " + viewState)
        }
    }

    override fun createPresenter(): MainPresenter {
       return presenter
    }

    override fun onUserClicked(user: User) {

    }

    private fun renderResult(result: List<User>) {
        rv_users.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        tv_empty.visibility = View.GONE
        tv_error.visibility = View.GONE
        adapter.users = result
        adapter.notifyDataSetChanged()
    }

    private fun renderSearchNotStarted() {
        rv_users.visibility = View.GONE
        progressBar.visibility = View.GONE
        tv_error.visibility = View.GONE
        tv_empty.visibility = View.GONE
    }

    private fun renderLoading() {
        rv_users.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        tv_empty.visibility = View.GONE
        tv_error.visibility = View.GONE
    }

    private fun renderError() {
        rv_users.visibility = View.GONE
        progressBar.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        tv_empty.visibility = View.GONE
    }

    private fun renderEmptyResult() {
        rv_users.visibility = View.GONE
        progressBar.visibility = View.GONE
        tv_error.visibility = View.GONE
        tv_empty.visibility = View.VISIBLE
    }
}
