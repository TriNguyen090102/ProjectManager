package com.example.projectmanager.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.projectmanager.Models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

const val USER_COLLECTION = "users"
class AuthRepository {


    private val firebase = FirebaseAuth.getInstance()
    private val mFirestore = FirebaseFirestore.getInstance()

    fun hasUser():Boolean = firebase.currentUser != null
    fun getCurrentUserId():String = firebase.currentUser?.uid.orEmpty()
    fun createUser(userInfo: User){
        Log.d("currentID","${getCurrentUserId()}")
        val currentID = getCurrentUserId()
        if(!currentID.isNullOrEmpty()){
            mFirestore.collection(USER_COLLECTION)
                .document(getCurrentUserId())
                .set(userInfo, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d("created","")
                }
                .addOnFailureListener {
                    Log.d("created fail","")
                }
        }

    }
    fun registerUser(name: String, email: String, password: String)  {
        val name = name.trim { it <= ' ' }
        val email = email.trim { it <= ' ' }
        val password = password.trim { it <= ' ' }


        firebase
            .createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    Log.d("Register successful","email: $email")
                    val user = User(id= firebaseUser.uid,email =registeredEmail,name=name)
                    createUser(user)

                } else {
                    Log.d("Register error","")

                }
            }
    }

    fun getUser(userid : String){
        mFirestore.collection(USER_COLLECTION)
            .document(userid)
            .get()
            .addOnSuccessListener { userDocument ->
                val loggedinUser  = userDocument.toObject(User::class.java)
                Log.d("Logged In","")
            }
            .addOnFailureListener {
                Log.d("Log in fail","")
            }

    }

    fun signInRegisteredUser(email: String,password: String){
        val email = email.trim { it <= ' ' }
        val password = password.trim { it <= ' ' }

        firebase
            .signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val user = firebase.currentUser
                    Log.d("Sign in successful","email: $email")
                    Log.d("Sign in successful","password: $password")
                    getUser(user?.uid.toString())
                } else {
                    Log.d("sign in error","")
                }
            }

    }
}