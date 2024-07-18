package com.example.mobileproject.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileproject.R
import com.example.mobileproject.data.SportifyDatabase
import com.example.mobileproject.presentation.UserViewModel
import com.example.mobileproject.data.UserViewModelFactory
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(
            SportifyDatabase.getDatabase(
                LocalContext.current
            ).userDao
        )
    ),
    navController: NavController
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var showPassword by remember {
        mutableStateOf(false)
    }

    var showPassword2 by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentWidth()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sportify),
            contentDescription = "",
            modifier = Modifier.size(width = 100.dp, height = 100.dp)
        )
        Text(
            text = "Sporty Account Registration",
            fontSize = 30.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(20.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") }
        )

        Spacer(modifier = Modifier.size(20.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username") }
        )

        Spacer(modifier = Modifier.size(20.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(text = "Password")
            },
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                Icon(
                    painter = if (showPassword) {
                        painterResource(id = R.drawable.hide)
                    } else {
                        painterResource(id = R.drawable.view)
                    },
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = { showPassword = !showPassword }),
                    contentDescription = ""
                )
            }
        )

        Spacer(modifier = Modifier.size(20.dp))

        TextField(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            label = {
                Text(text = "Repeat password")
            },
            visualTransformation = if (showPassword2) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                Icon(
                    painter = if (showPassword2) {
                        painterResource(id = R.drawable.hide)
                    } else {
                        painterResource(id = R.drawable.view)
                    },
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = { showPassword2 = !showPassword2 }),
                    contentDescription = ""
                )
            }
        )

        Spacer(modifier = Modifier.size(5.dp))

        TextButton(
            onClick = {
                navController.navigate("LoginScreen")
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Already have an account?")
        }

        Spacer(modifier = Modifier.size(20.dp))

        Button(onClick = {
            if (password == repeatPassword) {
                userViewModel.registerUser(username, email, password)
                navController.navigate("LoginScreen")
            } else {
                // Handle password mismatch
            }
        }) {
            Text(
                text = "Register",
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    val navController = rememberNavController()
    RegistrationScreen(navController = navController)
}
