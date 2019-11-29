package com.example.tmdbappfinalbymusicmanager.pojos.FindTrendingTvShowsDailyClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FindTrendingTvShowsDaily {
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Result>? =
        null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null

}