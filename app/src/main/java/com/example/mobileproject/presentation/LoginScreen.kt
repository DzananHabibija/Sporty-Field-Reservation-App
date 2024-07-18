package com.example.project.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobileproject.R
import com.example.mobileproject.data.SportifyDatabase
import com.example.mobileproject.presentation.UserViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var isError by remember{ mutableStateOf(false) }

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
        Spacer(modifier = Modifier.size(width = 0.dp, height = -40.dp))

        Text(
            text = "Sporty Reservation App",
            fontSize = 30.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(text = "Email")
            },
            placeholder = {
                Text(text = "example@exmaple.com")
            },
            isError = isError
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(text = "Password")
            },
            visualTransformation = if(showPassword){
                VisualTransformation.None
            }
            else{
                PasswordVisualTransformation()
            },
            trailingIcon = {
                Icon(
                    painter =  if(showPassword){
                        painterResource(id = R.drawable.hide)
                    }
                    else{
                        painterResource(id = R.drawable.view)
                    },
                    modifier = Modifier.size(24.dp).clickable(onClick = {showPassword=!showPassword}),
                    contentDescription = ""
                )
            },
            isError = isError
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 5.dp))

        TextButton(
            onClick =  {navController.navigate("RegistrationScreen")},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Don't have an account?")
        }

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        Button(onClick = {
            val user = userViewModel.loginUser(email, password)

            if (user != null) {
                var index = user.id;
                navController.navigate("HomepageScreen/$index")
            } else {
                isError = true
            }
        }) {
            Text(
                text = "Login",
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    //LoginScreen(userViewModel = UserViewModel(SportifyDatabase.getDatabase(LocalContext.current).userDao), navController = )
}
