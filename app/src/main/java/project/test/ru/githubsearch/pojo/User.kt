package project.test.ru.githubsearch.pojo


data class SearchResponse(val total_count: Int, val items: List<User>)

data class User(val id: Int, val login: String, val avatar_url: String)

