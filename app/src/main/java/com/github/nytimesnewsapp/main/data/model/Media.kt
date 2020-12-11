package com.github.nytimesnewsapp.main.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media (
	@SerialName("media-metadata")
	val mediaMetadata : List<MediaMetadata>,
)