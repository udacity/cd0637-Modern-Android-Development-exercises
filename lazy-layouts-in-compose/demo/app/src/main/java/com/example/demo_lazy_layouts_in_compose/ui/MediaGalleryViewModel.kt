package com.example.demo_lazy_layouts_in_compose.ui

import androidx.lifecycle.ViewModel
import com.example.demo_lazy_layouts_in_compose.data.MediaItem
import com.example.demo_lazy_layouts_in_compose.data.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class MediaGalleryViewModel(
    repository: MediaRepository = MediaRepository()
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val allMedia = repository.getMediaItems()
    val categories = repository.getCategories()

    // Filter media based on selected category
    val filteredMedia = _selectedCategory.map { category ->
        if (category == "All") {
            allMedia
        } else {
            allMedia.filter { it.category == category }
        }
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }
}
