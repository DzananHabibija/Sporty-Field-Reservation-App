package com.example.mobileproject.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileproject.presentation.uiElements.dropdownmenu
import com.example.mobileproject.presentation.uiElements.textfield

@Composable
fun AddFieldScreen(
    state: FieldState,
    navController: NavController,
    onEvent: (FieldsEvent) -> Unit
) {
    val sportList = listOf("Football", "Basketball", "Volleyball", "Tenis", "Other")
    val cityList = listOf("Sarajevo", "Mostar", "Banja Luka", "Visoko", "Tuzla", "Zenica", "Tešanj", "")

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(FieldsEvent.SaveField(
                    title = state.title.value,
                    sport = state.sport.value,
                    city = state.city.value,
                    price = state.price.value,
                    time = state.time.value,
                ))
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Save Field"
                )
            }
        }
    ) { paddingValues ->
        val paddingValues = 32.dp
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Add Field Listing",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Divider(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))

            textfield(
                placeholderText = "Listing Title",
                fieldToUpdate = state.title,
                suffix = "",
                keyboardType = KeyboardType.Text
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                dropdownmenu(placeholderText = "Sport", list = sportList, fieldToUpdate = state.sport)
                Spacer(modifier = Modifier.width(8.dp))
                dropdownmenu(placeholderText = "City", list = cityList, fieldToUpdate = state.city)
            }
            Row {
                // price time
            }
            textfield(
                placeholderText = "Price",
                fieldToUpdate = state.price,
                suffix = "KM",
                keyboardType = KeyboardType.Number
            )
            textfield(
                placeholderText = "Time",
                fieldToUpdate = state.time,
                suffix = "min",
                keyboardType = KeyboardType.Number
            )
        }
    }
}

@Preview
@Composable
fun AddFieldsScreenPreview() {
    // AddFieldScreen()
}




