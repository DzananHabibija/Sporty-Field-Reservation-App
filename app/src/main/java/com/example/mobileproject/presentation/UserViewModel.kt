package com.example.mobileproject.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileproject.data.User
import com.example.mobileproject.data.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult.asStateFlow()

    fun registerUser(username: String, email: String, password: String) {
        val user = User(
            username = username,
            email = email,
            password = password
        )
        viewModelScope.launch {
            userDao.insert(user)
        }
    }



//    fun loginUser(email: String, password: String) {
//        viewModelScope.launch {
//            val user = userDao.getUserByEmail(email,password)
//            _loginResult.value = user?.password == password
//        }
//    }
fun loginUser(email: String, password: String): User? {
    return runBlocking {
        userDao.getUserByEmail(email, password)
    }
}


    fun resetLoginResult() {
        _loginResult.value = null
    }

}

