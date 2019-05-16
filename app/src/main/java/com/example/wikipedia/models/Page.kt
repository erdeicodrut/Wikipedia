package com.example.wikipedia.models

class Page {
    var pageid: Int? = null
    var title: String? = null
    var fullurl: String? = null
    var thumbnail: Thumbnail? = null
    override fun toString(): String {
        return "Page(pageid=$pageid, title=$title, fullurl=$fullurl, thumbnail=$thumbnail)"
    }


}