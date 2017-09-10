package project.test.ru.githubsearch.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import project.test.ru.githubsearch.R
import project.test.ru.githubsearch.pojo.User

class SearchViewHolder(itemView: View, listener: UserClickedListener) : RecyclerView.ViewHolder(itemView) {

    interface UserClickedListener {
        fun onUserClicked(user: User)
    }

    init {
        itemView.setOnClickListener { v -> user?.let { listener.onUserClicked(it) } }
    }

    private var user: User? = null

    fun bind(user: User) {
        this.user = user
        Glide.with(itemView.context)
                .load(user.avatar_url)
                .centerCrop()
                .into(itemView.findViewById(R.id.iv_avatar))
        itemView.findViewById<TextView>(R.id.tv_user_name).text = user.login
    }
}