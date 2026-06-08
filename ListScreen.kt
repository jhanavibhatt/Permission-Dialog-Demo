package com.jhanavi.shayariverse.presentation.ui.listscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jhanavi.shayariverse.QuoteRepository
import com.jhanavi.shayariverse.model.Quote
import com.jhanavi.shayariverse.model.Shayri
import com.jhanavi.shayariverse.presentation.navigation.CategoryType
import com.jhanavi.shayariverse.presentation.ui.listscreen.components.QuoteItemCard
import com.jhanavi.shayariverse.presentation.ui.listscreen.components.ShayriItemCard
import com.jhanavi.shayariverse.ui.theme.ScreenBg
import com.jhanavi.shayariverse.viewmodel.FavoriteViewModel
import kotlin.collections.filter


@Composable
fun ListScreen(
    category: String?,
    type: CategoryType,
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val allQuotes by produceState<List<Any>>(
        initialValue = emptyList(),
        key1 = type
    ) {
        value = when (type) {
            CategoryType.QUOTES -> QuoteRepository.loadQuotes(context)
            CategoryType.SHAYARI -> QuoteRepository.loadShayri(context)
        }
    }

    val filteredQuotes = remember(category, allQuotes) {
        if (category == null) {
            allQuotes
        } else {
            allQuotes.filter { item ->
                when (item) {
                    is Quote -> item.category.equals(category, ignoreCase = true)
                    is Shayri -> item.category.equals(category, ignoreCase = true)
                    else -> false
                }
            }
        }
    }

    ListScreenContent(filteredQuotes = filteredQuotes, viewModel = viewModel)
}

@Composable
fun ListScreenContent(
    filteredQuotes: List<Any>,
    viewModel: FavoriteViewModel? = null // Optional for Preview
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg)
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredQuotes) { item ->
                if (viewModel != null) {
                    when (item) {
                        is Quote -> QuoteItemCard(item, viewModel)
                        is Shayri -> ShayriItemCard(item, viewModel)
                    }
                } else {
                    // Placeholder for Preview when ViewModel is null
                    Text("Item: $item", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListScreenPreview() {
    ListScreenContent(
        filteredQuotes = listOf(
            Shayri(shayri = "Sample Shayari", category = "Love"),
            Quote(quote = "Sample Quote", author = "Author", category = "Life")
        )
    )
}
