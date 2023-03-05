class Film {
    var id: Int? = 0
    var title: String? = ""
    var poster_path: String? = ""
    var genres: List<Genre>? = listOf(Genre())
    var overview: String? = ""
    var release_date: String? = ""
}

class Genre{ val name: String? = null }

