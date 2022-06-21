package com.isilon.beinconnect.data.model

data class Model(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)