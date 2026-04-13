package com.example.demo_lazy_layouts_in_compose.data

import java.util.UUID

data class MediaItem(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val category: String,
    val imageUrl: String
)
