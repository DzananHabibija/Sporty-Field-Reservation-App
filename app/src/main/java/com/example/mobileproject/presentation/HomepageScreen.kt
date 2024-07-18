package com.example.mobileproject.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.example.mobileproject.R
import com.example.mobileproject.presentation.uiElements.FieldCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageScreen(
    state: FieldState,
    id: Int,
    navController: NavController,
    onEvent: (FieldsEvent) -> Unit
) {

    Scaffold(
        topBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ){
                Text(
                    text = stringResource(id = R.string.app_name ),
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )




                IconButton(onClick = { navController.navigate("ReservesPageScreen/$id") }) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = "Sort fields",
                        modifier = Modifier.size(35.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.title.value = ""
                state.sport.value = ""
                state.city.value = ""
                state.price.value = ""
                state.time.value = ""
                navController.navigate("AddFieldScreen")
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add New Field")
            }
        }
    ) { paddingValues ->

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.fields.size, ) { index ->
                Log.d("message", state.fields[index].sport)
                FieldCard(
                    state = state,
                    index = index,
                    onEvent = onEvent,
                    userId = id,
                    navController = navController
                )
            }
        }
    }
}



fun getCardImage(sport: String): Int {
    val sport = sport.lowercase()
    return when (sport) {
        "football" -> R.drawable.football_img
        "basketball" -> R.drawable.basketball_img
        "volleyball" -> R.drawable.volleyball_img
        "tenis" -> R.drawable.tenis_img
        else -> R.drawable.noimg_img // TODO Attempt fix, ne radi else image
    }
}

@Composable
fun getCardSportIcon(sport: String): ImageVector {
    val sport = sport.lowercase()
    return when (sport) {
        "football" -> ImageVector.vectorResource(id = R.drawable.baseline_sports_soccer_24)
        "basketball" -> ImageVector.vectorResource(id = R.drawable.baseline_sports_basketball_24)
        "volleyball" -> ImageVector.vectorResource(id = R.drawable.baseline_sports_volleyball_24)
        "tenis" -> ImageVector.vectorResource(id = R.drawable.baseline_sports_tennis_24)
        else -> ImageVector.Builder(
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).build()
    }
}

