package project.test.ru.githubsearch.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import project.test.ru.githubsearch.R
import project.test.ru.githubsearch.pojo.User


internal class SearchAdapter(private val inflater: LayoutInflater, private val productClickedListener: SearchViewHolder.UserClickedListener)
    : RecyclerView.Adapter<SearchViewHolder>() {
    var users: List<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(inflater.inflate(R.layout.user_item, parent, false), productClickedListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        users?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int = users?.let { it.size } ?: 0
}