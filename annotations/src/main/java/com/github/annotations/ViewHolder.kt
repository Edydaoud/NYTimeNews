package com.github.annotations

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class ViewHolder(
    val layout: String,
    val viewType:Int
)