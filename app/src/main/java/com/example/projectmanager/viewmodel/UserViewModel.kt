package com.example.projectmanager.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.projectmanager.repository.AuthRepository
import com.example.projectmanager.utils.Validation.state.UserState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.rpc.context.AttributeContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.notYetLogin)
    val user = _userState.asStateFlow()

    private val currentUserId = AuthRepository().getCurrentUserId()
    fun getCurUserId(): String = currentUserId

    fun getcurrentUser() {
        if (!currentUserId.isNullOrEmpty()) {
            Log.d("curruseridUserViewModel", "$currentUserId")
            AuthRepository().getUserById(currentUserId)
            { user ->
                Log.d("userViewModel", "${user?.name}")
                if (user != null) {
                    _userState.value = UserState.alreadyLogin(user)
                    Log.d("user state:", "logged in ${user.name}")
                } else {
                    Log.d("user state:", "fail to get user")
                }
            }
        } else {
            Log.d("dont have user", "")
        }
    }

    fun updateUser( name: String, email: String, mobile: String) {
        AuthRepository().update(name, email, mobile){

        }
    }

    fun updateProfileImage(){
        checkPremission()
    }

    private fun checkPremission() {

    }


    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        _userState.value = UserState.notYetLogin
    }
}

