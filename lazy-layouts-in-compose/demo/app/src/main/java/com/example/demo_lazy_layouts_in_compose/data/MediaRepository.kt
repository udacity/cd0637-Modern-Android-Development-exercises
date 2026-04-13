package com.example.demo_lazy_layouts_in_compose.data

class MediaRepository {
    fun getMediaItems(): List<MediaItem> {
        return listOf(
            MediaItem(title = "Mountain Peaks", category = "Nature", imageUrl = "https://example.com/nature1.jpg"),
            MediaItem(title = "City Skyline", category = "Architecture", imageUrl = "https://example.com/arch1.jpg"),
            MediaItem(title = "Forest Path", category = "Nature", imageUrl = "https://example.com/nature2.jpg"),
            MediaItem(title = "Modern Library", category = "Architecture", imageUrl = "https://example.com/arch2.jpg"),
            MediaItem(title = "Street Portait", category = "People", imageUrl = "https://example.com/people1.jpg"),
            MediaItem(title = "Ocean Wave", category = "Nature", imageUrl = "https://example.com/nature3.jpg"),
            MediaItem(title = "Old Bridge", category = "Architecture", imageUrl = "https://example.com/arch3.jpg"),
            MediaItem(title = "Walking in Park", category = "People", imageUrl = "https://example.com/people2.jpg"),
            MediaItem(title = "Starry Night", category = "Nature", imageUrl = "https://example.com/nature4.jpg"),
            MediaItem(title = "Glass Skyscraper", category = "Architecture", imageUrl = "https://example.com/arch4.jpg"),
            MediaItem(title = "Family Picnic", category = "People", imageUrl = "https://example.com/people3.jpg"),
            MediaItem(title = "Desert Dunes", category = "Nature", imageUrl = "https://example.com/nature5.jpg"),
            MediaItem(title = "Cathedral Dome", category = "Architecture", imageUrl = "https://example.com/arch5.jpg"),
            MediaItem(title = "Artist at Work", category = "People", imageUrl = "https://example.com/people4.jpg"),
            MediaItem(title = "River Bend", category = "Nature", imageUrl = "https://example.com/nature6.jpg")
        )
    }

    fun getCategories(): List<String> {
        return listOf("All", "Nature", "Architecture", "People")
    }
}
