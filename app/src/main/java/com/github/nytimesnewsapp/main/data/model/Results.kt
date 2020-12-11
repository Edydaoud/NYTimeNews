package com.github.nytimesnewsapp.main.data.model

import com.github.nytimesnewsapp.main.domain.NewsDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class Results(
    val id: Long?,
    val published_date: String?,
    val section: String?,
    val abstract: String?,
    val title: String?,
    val media: List<Media>,
    val byline: String?,
    val updated: String?
) {
    fun toDomain(): NewsDomainModel {
        return NewsDomainModel(
            id = id?.toString().orEmpty(),
            title = title.orEmpty(),
            date = published_date.orEmpty(),
            abstract = abstract.orEmpty(),
            section = section.orEmpty(),
            imageUri = media.firstOrNull()?.mediaMetadata?.lastOrNull()?.url.orEmpty(),
            author = byline.orEmpty(),
            lastModified = updated.orEmpty()
        )
    }
}