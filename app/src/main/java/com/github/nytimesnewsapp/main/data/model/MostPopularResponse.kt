package com.github.nytimesnewsapp.main.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MostPopularResponse (
	val status : String,
	val copyright : String,
	val num_results : Int,
	val results : List<Results>
)