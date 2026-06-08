package com.jhanavi.shayariverse.presentation.ui.favoritescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhanavi.shayariverse.R
import com.jhanavi.shayariverse.presentation.ui.favoritescreen.components.ContentCard
import com.jhanavi.shayariverse.ui.theme.ScreenBg
import com.jhanavi.shayariverse.viewmodel.FavoriteViewModel


data class ContentItem(
    val id: Int = 0,
    val text: String,
    val type: String, // "shayari" or "quote"
    val category: String? = null, // for shayari
    val author: String? = null,   // for quotes
    var isFavorite: Boolean = false
)
@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = hiltViewModel()) {
    val items by viewModel.favorites.collectAsState()
    FavoriteScreenContent(
        items = items.map { item ->
            ContentItem(
                id = item.id,
                text = item.text,
                type = item.type,
                category = item.category,
                author = item.author
            )
        },
        onDeleteClick = { id -> viewModel.deleteFavorite(id) }
    )
}

@Composable
fun FavoriteScreenContent(
    items: List<ContentItem>,
    onDeleteClick: (Int) -> Unit
) {
    var selectedTab by remember { mutableStateOf("shayari") }

    Column(modifier = Modifier.fillMaxSize().background(ScreenBg)) {
        // Tabs
        Row(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFF2A1E3D), RoundedCornerShape(50))
        ) {
            TabButton(stringResource(R.string.shayari), selectedTab == "shayari") {
                selectedTab = "shayari"
            }
            TabButton(stringResource(R.string.quotes), selectedTab == "quote") {
                selectedTab = "quote"
            }
        }

        LazyColumn {
            items(items.filter { it.type == selectedTab }) { item ->
                ContentCard(
                    item = item,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}

@Composable
fun TabButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) Color(0xFF7B2CBF) else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Gray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreenContent(
        items = listOf(
            ContentItem(1, "Love is life", "shayari", "Love"),
            ContentItem(2, "Success is a journey", "quote", author = "Unknown")
        ),
        onDeleteClick = {}
    )
}