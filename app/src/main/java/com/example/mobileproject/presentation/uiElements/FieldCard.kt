package com.example.mobileproject.presentation.uiElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileproject.R
import com.example.mobileproject.presentation.FieldState
import com.example.mobileproject.presentation.FieldsEvent
import com.example.mobileproject.presentation.getCardImage
import com.example.mobileproject.presentation.getCardSportIcon
import com.example.mobileproject.presentation.FieldScreen


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FieldCard(
    state: FieldState,
    index: Int,
    navController: NavController,
    userId: Int?,
    onEvent: (FieldsEvent) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp


    Card(
        modifier = Modifier
            .size(width = screenWidth - 24.dp, height = 100.dp),
        onClick = {
            navController.navigate("FieldScreen/$index/$userId")
        }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = getCardImage(state.fields[index].sport)),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 100.dp, height = 100.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ) {
                    Text(
                        text = state.fields[index].title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            onEvent(FieldsEvent.DeleteField(state.fields[index]))
                        },
                        modifier = Modifier.size(21.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete Field",
                            modifier = Modifier.size(53.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                Divider(
                    color = Color.Black,
                    thickness = 0.5.dp,
                )
                Row {
                    Column {
                        Row(modifier = Modifier.padding(top = 11.dp)) {
                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = getCardSportIcon(state.fields[index].sport),
                                        contentDescription = "Sport icon",
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Text(
                                        text = state.fields[index].sport,
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.baseline_location_pin_24),
                                        contentDescription = "Location icon",
                                        modifier = Modifier.size(15.dp),
                                        tint = Color.Red
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))
                                    Text(
                                        text = state.fields[index].city,
                                    )
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = state.fields[index].price + " KM / " + state.fields[index].time + " min",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}