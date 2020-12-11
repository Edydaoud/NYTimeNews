package com.github.nytimesnewsapp.main.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MediaMetadata (
	val url : String?,
	val format : String?,
)