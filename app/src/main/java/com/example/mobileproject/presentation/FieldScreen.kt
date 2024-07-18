package com.example.mobileproject.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobileproject.R
import com.example.mobileproject.data.Field
import com.example.mobileproject.data.SportifyDatabase
import java.text.SimpleDateFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldScreen(
    state: FieldState,
    index: Int,
    userId: Int?,
    navController: NavController,
    onEvent: (FieldsEvent) -> Unit,
    reservationViewModel : ReservationsViewModel = viewModel(factory = ReservationViewModelFactory(SportifyDatabase.getDatabase(LocalContext.current).reservationdao))
) {
    val datestate = rememberDatePickerState()
    var openCalendar by remember { mutableStateOf(false) }
    var showDate by remember { mutableStateOf(false) }
    var openTimePicker by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("") }
    var showTime by remember { mutableStateOf(false)}

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { openCalendar = true }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "open Calendar")
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)  // Apply the paddingValues provided by Scaffold
                .padding(horizontal = 20.dp, vertical = 15.dp)
        ) {
            if (openCalendar) {
                DatePickerDialog(
                    onDismissRequest = { openCalendar = false },
                    confirmButton = {
                        Button(onClick = {
                            showDate = true
                            openCalendar = false
                            openTimePicker = true // Open time picker after confirming date
                        }) {
                            Text(text = "Confirm")
                        }
                    },
                ) {
                    DatePicker(
                        state = datestate
                    )
                }
            }

            if (openTimePicker) {
                TimePickerDialog(
                    onDismissRequest = { openTimePicker = false },
                    onConfirm = { time ->
                        selectedTime = time
                        openTimePicker = false
                        showTime = true
                    }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(2.dp)
                    .background(color = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = getCardImage(state.fields[index].sport)),
                    contentDescription = "Field",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.size(10.dp))
            Divider()
            Spacer(modifier = Modifier.size(5.dp))

            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = state.fields[index].title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sport",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = colorResource(id = R.color.bluish)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.fields[index].sport,
                            color = Color.White,
                            fontSize = 30.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Location",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = colorResource(id = R.color.bluish)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            state.fields[index].city,
                            color = Color.White,
                            fontSize = 30.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.fields[index].price + " KM / " + state.fields[index].time +" min",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Divider()
            Spacer(modifier = Modifier.size(15.dp))

            if(showTime){
                Column (){
                    val formatter: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
                    val dateString = formatter.format(datestate.selectedDateMillis)
                    Text(
                        text = "Date you picked $dateString",
                        fontSize = 25.sp
                    )
                    Text(
                        text = "Time you picked $selectedTime",
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center // Center content horizontally
                    ) {
                        Button(
                            onClick = {
                                if (userId != null) {
                                    reservationViewModel.reserve(
                                        state.fields[index].title,
                                        state.fields[index].sport,
                                        state.fields[index].city,
                                        state.fields[index].price,
                                        userId,
                                        dateString,
                                        selectedTime,
                                        state.fields[index].time
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .size(width = 200.dp, height = 50.dp), // Custom size
                        ) {
                            Text(
                                text = "Reserve Field",
                                color = Color.White,
                                fontSize = 21.sp
                            )
                        }
                    }
                }
            }
            else{
                Column (){
                    Text(
                        text = "Hint: Click on the floating action button to reserve a field",
                        color = Color.Gray,
                        fontSize = 20.sp
                    )
                }
            }


        }
    }
}

@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val hours = remember { mutableStateOf(12) }
    val minutes = remember { mutableStateOf(0) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Select Time", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Hours Picker
                Text(text = "Hours", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { if (hours.value > 0) hours.value -= 1 }) {
                        Text("-")
                    }
                    Text(text = "${hours.value}".padStart(2, '0'), fontSize = 24.sp, modifier = Modifier.padding(horizontal = 8.dp))
                    Button(onClick = { if (hours.value < 23) hours.value += 1 }) {
                        Text("+")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Minutes Picker
                Text(text = "Minutes", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { if (minutes.value > 0) minutes.value -= 1 }) {
                        Text("-")
                    }
                    Text(
                        text = "${minutes.value}".padStart(2, '0'), // Pad with leading zero if needed
                        fontSize = 24.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Button(onClick = { if (minutes.value < 59) minutes.value += 1 }) {
                        Text("+")
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm("${hours.value}:${minutes.value.toString().padStart(2, '0')}") }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    )
}

