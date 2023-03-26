warning: LF will be replaced by CRLF in app/src/main/res/drawable/ic_nav_menu.xml.
The file will have its original line endings in your working directory
warning: LF will be replaced by CRLF in app/src/main/res/drawable/ic_user_placeholder.xml.
The file will have its original line endings in your working directory
[1mdiff --git a/.idea/deploymentTargetDropDown.xml b/.idea/deploymentTargetDropDown.xml[m
[1mindex a982b4b..86f6d52 100644[m
[1m--- a/.idea/deploymentTargetDropDown.xml[m
[1m+++ b/.idea/deploymentTargetDropDown.xml[m
[36m@@ -12,6 +12,6 @@[m
         </deviceKey>[m
       </Target>[m
     </targetSelectedWithDropDown>[m
[31m-    <timeTargetWasSelectedWithDropDown value="2023-03-19T13:07:04.186334900Z" />[m
[32m+[m[32m    <timeTargetWasSelectedWithDropDown value="2023-03-23T06:11:41.710007500Z" />[m
   </component>[m
 </project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/misc.xml b/.idea/misc.xml[m
[1mindex 2a4d5b5..b4c310b 100644[m
[1m--- a/.idea/misc.xml[m
[1m+++ b/.idea/misc.xml[m
[36m@@ -1,5 +1,17 @@[m
 <?xml version="1.0" encoding="UTF-8"?>[m
 <project version="4">[m
[32m+[m[32m  <component name="DesignSurface">[m
[32m+[m[32m    <option name="filePathToZoomLevelMap">[m
[32m+[m[32m      <map>[m
[32m+[m[32m        <entry key="..\:/Users/ntr68/AndroidStudioProjects/ProjectManager/app/src/main/res/drawable-v24/ic_launcher_foreground.xml" value="0.1" />[m
[32m+[m[32m        <entry key="..\:/Users/ntr68/AndroidStudioProjects/ProjectManager/app/src/main/res/drawable/ic_launcher_background.xml" value="0.1" />[m
[32m+[m[32m        <entry key="..\:/Users/ntr68/AndroidStudioProjects/ProjectManager/app/src/main/res/drawable/ic_nav_menu.xml" value="0.1" />[m
[32m+[m[32m        <entry key="..\:/Users/ntr68/AndroidStudioProjects/ProjectManager/app/src/main/res/drawable/ic_nav_sign_out.xml" value="0.1" />[m
[32m+[m[32m        <entry key="..\:/Users/ntr68/AndroidStudioProjects/ProjectManager/app/src/main/res/drawable/ic_nav_user.xml" value="0.1" />[m
[32m+[m[32m        <entry key="..\:/Users/ntr68/AndroidStudioProjects/ProjectManager/app/src/main/res/drawable/ic_user_placeholder.xml" value="0.1" />[m
[32m+[m[32m      </map>[m
[32m+[m[32m    </option>[m
[32m+[m[32m  </component>[m
   <component name="ProjectRootManager" version="2" languageLevel="JDK_11" default="true" project-jdk-name="Android Studio default JDK" project-jdk-type="JavaSDK">[m
     <output url="file://$PROJECT_DIR$/build/classes" />[m
   </component>[m
[1mdiff --git a/app/build.gradle b/app/build.gradle[m
[1mindex ac3ce74..767fcfd 100644[m
[1m--- a/app/build.gradle[m
[1m+++ b/app/build.gradle[m
[36m@@ -78,8 +78,8 @@[m [mdependencies {[m
     implementation "com.google.firebase:firebase-auth:19.2.0"[m
     implementation "com.google.firebase:firebase-firestore:24.4.4"[m
 [m
[31m-[m
[31m-[m
[32m+[m[32m    // Coil Image Loading[m
[32m+[m[32m    implementation("io.coil-kt:coil-compose:1.3.0")[m
     // Dagger Hilt[m
     implementation("com.google.dagger:hilt-android:2.41")[m
     kapt("com.google.dagger:hilt-android-compiler:2.41")[m
[36m@@ -88,4 +88,6 @@[m [mdependencies {[m
     implementation("androidx.hilt:hilt-navigation-compose:1.0.0")[m
     implementation("androidx.hilt:hilt-common:1.0.0")[m
     kapt("com.google.dagger:hilt-compiler:2.41")[m
[32m+[m[32m    //dexter[m
[32m+[m[32m    implementation 'com.karumi:dexter:6.2.3'[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/AndroidManifest.xml b/app/src/main/AndroidManifest.xml[m
[1mindex ac06100..983845f 100644[m
[1m--- a/app/src/main/AndroidManifest.xml[m
[1m+++ b/app/src/main/AndroidManifest.xml[m
[36m@@ -15,16 +15,15 @@[m
         android:label="@string/app_name"[m
         android:roundIcon="@mipmap/ic_launcher_round"[m
         android:supportsRtl="true"[m
[31m-        android:theme="@style/Theme.ProjectManager"[m
         tools:targetApi="31">[m
         <activity[m
[32m+[m
             android:name=".MainActivity"[m
             android:exported="true"[m
             android:label="@string/app_name"[m
             android:theme="@style/Theme.ProjectManager">[m
             <intent-filter>[m
                 <action android:name="android.intent.action.MAIN" />[m
[31m-[m
                 <category android:name="android.intent.category.LAUNCHER" />[m
             </intent-filter>[m
         </activity>[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/MainActivity.kt b/app/src/main/java/com/example/projectmanager/MainActivity.kt[m
[1mindex b3927f1..6f5a704 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/MainActivity.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/MainActivity.kt[m
[36m@@ -4,7 +4,10 @@[m [mimport android.os.Bundle[m
 import androidx.activity.ComponentActivity[m
 import androidx.activity.compose.setContent[m
 import androidx.compose.ui.ExperimentalComposeUiApi[m
[32m+[m[32mimport androidx.hilt.navigation.compose.hiltViewModel[m
 import com.example.projectmanager.navigation.NavGraph[m
[32m+[m
[32m+[m[32mimport com.example.projectmanager.viewmodel.UserViewModel[m
 import com.google.firebase.FirebaseApp[m
 import dagger.hilt.EntryPoint[m
 import dagger.hilt.android.AndroidEntryPoint[m
[36m@@ -16,7 +19,8 @@[m [mclass MainActivity : ComponentActivity() {[m
 [m
         super.onCreate(savedInstanceState)[m
         setContent {[m
[31m-            NavGraph()[m
[32m+[m[32m            val userViewModel: UserViewModel = hiltViewModel()[m
[32m+[m[32m            NavGraph(userViewModel)[m
         }[m
     }[m
 }[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/Models/User.kt b/app/src/main/java/com/example/projectmanager/Models/User.kt[m
[1mindex 1b3af5e..31a04b3 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/Models/User.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/Models/User.kt[m
[36m@@ -2,13 +2,13 @@[m [mpackage com.example.projectmanager.Models[m
 [m
 import kotlinx.serialization.Serializable[m
 [m
[31m-[m
[32m+[m[32m@Serializable[m
 data class User([m
[31m-    val id: String = "",[m
[31m-    val email: String = "",[m
[32m+[m[32m    @Transient val id: String = "",[m
[32m+[m[32m    @Transient val email: String = "",[m
 [m
[31m-    val name: String = "",[m
[31m-    val image: String = "",[m
[31m-    val mobile: Long = 0,[m
[31m-    val fcmToken: String = ""[m
[32m+[m[32m    @Transient val name: String = "",[m
[32m+[m[32m    @Transient val image: String = "",[m
[32m+[m[32m    @Transient val mobile: Long = 0,[m
[32m+[m[32m    @Transient val fcmToken: String = ""[m
 )[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/app/ProjectManager.kt b/app/src/main/java/com/example/projectmanager/app/ProjectManager.kt[m
[1mindex 6a98b29..bdbe995 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/app/ProjectManager.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/app/ProjectManager.kt[m
[36m@@ -1,9 +1,13 @@[m
 package com.example.projectmanager.app[m
 [m
 import android.app.Application[m
[32m+[m[32mimport com.example.projectmanager.viewmodel.UserViewModel[m
 import dagger.hilt.android.HiltAndroidApp[m
[32m+[m[32mimport javax.inject.Inject[m
[32m+[m[32mimport javax.inject.Singleton[m
 [m
 [m
 @HiltAndroidApp[m
 class ProjectManager : Application() {[m
[32m+[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/component/InfomationField.kt b/app/src/main/java/com/example/projectmanager/component/InfomationField.kt[m
[1mindex e4258b4..ce4c8f2 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/component/InfomationField.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/component/InfomationField.kt[m
[36m@@ -16,17 +16,20 @@[m [mimport androidx.compose.ui.unit.dp[m
 fun InformationField([m
     label: String, keyboardType: KeyboardType,[m
     visualTransformation: VisualTransformation = VisualTransformation.None,[m
[31m-    onValueChange: (String) -> Unit[m
[32m+[m[32m    onValueChange: (String) -> Unit,[m
[32m+[m[32m    rememberTextValue: String = ""[m
 ) {[m
[31m-    var text by remember { mutableStateOf("") }[m
[32m+[m[32m    var text by remember { mutableStateOf(rememberTextValue) }[m
     OutlinedTextField([m
         value = text,[m
         modifier = Modifier[m
             .padding(top = 5.dp, start = 5.dp, end = 5.dp)[m
             .fillMaxWidth()[m
             .background(Color.White),[m
[31m-        onValueChange = { text = it[m
[31m-                        onValueChange(it)},[m
[32m+[m[32m        onValueChange = {[m
[32m+[m[32m            text = it[m
[32m+[m[32m            onValueChange(it)[m
[32m+[m[32m        },[m
         visualTransformation = visualTransformation,[m
         label = { Text("$label") },[m
         keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/component/TopBar.kt b/app/src/main/java/com/example/projectmanager/component/TopBar.kt[m
[1mindex 47ea1fc..588d1ef 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/component/TopBar.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/component/TopBar.kt[m
[36m@@ -1,5 +1,6 @@[m
 package com.example.projectmanager.component[m
 [m
[32m+[m[32mimport androidx.compose.foundation.background[m
 import androidx.compose.foundation.clickable[m
 import androidx.compose.foundation.layout.*[m
 import androidx.compose.material.Icon[m
[36m@@ -11,27 +12,28 @@[m [mimport androidx.compose.runtime.Composable[m
 import androidx.compose.ui.Alignment[m
 import androidx.compose.ui.Modifier[m
 import androidx.compose.ui.graphics.Color[m
[32m+[m[32mimport androidx.compose.ui.graphics.vector.ImageVector[m
 import androidx.compose.ui.unit.dp[m
 import androidx.compose.ui.unit.sp[m
 import com.example.projectmanager.navigation.MainActions[m
 [m
 @Composable[m
[31m-fun TopBar(title: String, action: MainActions) {[m
[32m+[m[32mfun TopBar(title: String, onClick:()->Unit, icon:ImageVector = Icons.Default.ArrowBack) {[m
     Row([m
         modifier = Modifier[m
             .fillMaxWidth()[m
[31m-            .padding(start = 16.dp, top = 16.dp, end = 16.dp)[m
[31m-            .clickable { action.upPress() },[m
[32m+[m[32m            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)[m
[32m+[m[32m            .clickable { onClick },[m
[32m+[m
         horizontalArrangement = Arrangement.Start,[m
         verticalAlignment = Alignment.CenterVertically[m
     ) {[m
         Icon([m
[31m-            imageVector = Icons.Default.ArrowBack,[m
[32m+[m[32m            imageVector = icon,[m
             contentDescription = "",[m
[31m-            modifier = Modifier.clickable(onClick = { action.upPress() })[m
[32m+[m[32m            modifier = Modifier.clickable(onClick = onClick)[m
         )[m
         Spacer(modifier = Modifier.width(12.dp))[m
         Text(text = title, style = typography.h5, fontSize = 20.sp, color = Color(0xFF363A43))[m
[31m-[m
     }[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/navigation/NavGraph.kt b/app/src/main/java/com/example/projectmanager/navigation/NavGraph.kt[m
[1mindex 19bad4c..d7bd90d 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/navigation/NavGraph.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/navigation/NavGraph.kt[m
[36m@@ -1,27 +1,39 @@[m
 package com.example.projectmanager.navigation[m
 [m
[32m+[m[32mimport android.annotation.SuppressLint[m
 import androidx.compose.runtime.Composable[m
[32m+[m[32mimport androidx.compose.runtime.LaunchedEffect[m
 import androidx.compose.runtime.remember[m
 import androidx.compose.ui.platform.LocalContext[m
 import androidx.hilt.navigation.compose.hiltViewModel[m
[32m+[m[32mimport androidx.lifecycle.ViewModelStoreOwner[m
 import androidx.navigation.NavController[m
 import androidx.navigation.compose.NavHost[m
 import androidx.navigation.compose.composable[m
 import androidx.navigation.compose.rememberNavController[m
[32m+[m[32mimport com.example.projectmanager.Models.User[m
[32m+[m[32mimport com.example.projectmanager.utils.Validation.state.UserState[m
 import com.example.projectmanager.view.*[m
 import com.example.projectmanager.viewmodel.BaseValidationViewModel[m
 import com.example.projectmanager.viewmodel.UserViewModel[m
 [m
[32m+[m[32m@SuppressLint("StateFlowValueCalledInComposition")[m
 @Composable[m
[31m-fun NavGraph() {[m
[32m+[m[32mfun NavGraph(userViewModel: UserViewModel) {[m
     val navController = rememberNavController()[m
     val context = LocalContext.current[m
     val actions = remember(navController) { MainActions(navController) }[m
 [m
[32m+[m
[32m+[m
[32m+[m
[32m+[m
[32m+[m
[32m+[m
     NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {[m
         composable(Screen.SplashScreen.route) {[m
             val viewModel = hiltViewModel<BaseValidationViewModel>(it)[m
[31m-            val userViewModel = hiltViewModel<UserViewModel>(it)[m
[32m+[m[32m            //val userViewModel = hiltViewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)[m
             SplashCreen(actions = actions, userViewModel)[m
         }[m
         composable(Screen.LogInScreen.route) {[m
[36m@@ -37,10 +49,20 @@[m [mfun NavGraph() {[m
         }[m
 [m
         composable(Screen.UserScreen.route){[m
[31m-            UserScreen()[m
[32m+[m[32m            val viewModel = hiltViewModel<BaseValidationViewModel>(it)[m
[32m+[m[32m            userViewModel.getcurrentUser()[m
[32m+[m[32m            UserScreen(actions = actions, viewModel = viewModel, userViewModel = userViewModel)[m
[32m+[m[32m        }[m
[32m+[m[32m        composable(Screen.Profile.route){[m
[32m+[m[32m            val result = userViewModel.user.value[m
[32m+[m[32m            var user = User()[m
[32m+[m[32m            if(result is UserState.alreadyLogin)[m
[32m+[m[32m            {[m
[32m+[m[32m                user = result.data[m
[32m+[m[32m            }[m
[32m+[m[32m            ProfileScreen(actions = actions,user,userViewModel)[m
         }[m
     }[m
[31m-[m
 }[m
 [m
 class MainActions(private val navController: NavController) {[m
[36m@@ -61,4 +83,10 @@[m [mclass MainActions(private val navController: NavController) {[m
     fun gotoUser(){[m
         navController.navigate(Screen.UserScreen.route)[m
     }[m
[32m+[m
[32m+[m[32m    fun gotoUserProfile(){[m
[32m+[m[32m        navController.navigate(Screen.Profile.route)[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/navigation/Screen.kt b/app/src/main/java/com/example/projectmanager/navigation/Screen.kt[m
[1mindex f1a610a..ab0f7e5 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/navigation/Screen.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/navigation/Screen.kt[m
[36m@@ -6,4 +6,5 @@[m [msealed class Screen(val route: String) {[m
     object SignUpScreen : Screen("SignUp_screen")[m
     object SignInScreen : Screen("SignIn_screen")[m
     object UserScreen : Screen("User_screen")[m
[32m+[m[32m    object Profile : Screen("Profile_screen")[m
 }[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/repository/AuthRepository.kt b/app/src/main/java/com/example/projectmanager/repository/AuthRepository.kt[m
[1mindex 7421d34..7bbb92e 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/repository/AuthRepository.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/repository/AuthRepository.kt[m
[36m@@ -10,81 +10,106 @@[m [mimport com.google.firebase.firestore.FirebaseFirestore[m
 import com.google.firebase.firestore.SetOptions[m
 [m
 const val USER_COLLECTION = "users"[m
[31m-class AuthRepository {[m
 [m
 [m
[32m+[m[32mclass AuthRepository {[m
[32m+[m
     private val firebase = FirebaseAuth.getInstance()[m
     private val mFirestore = FirebaseFirestore.getInstance()[m
 [m
[31m-    fun hasUser():Boolean = firebase.currentUser != null[m
[31m-    fun getCurrentUserId():String = firebase.currentUser?.uid.orEmpty()[m
[31m-    fun createUser(userInfo: User){[m
[31m-        Log.d("currentID","${getCurrentUserId()}")[m
[32m+[m[32m    var currentUser: User? = null[m
[32m+[m[32m    fun hasUser(): Boolean = firebase.currentUser != null[m
[32m+[m[32m    fun getCurrentUserId(): String = firebase.currentUser?.uid.orEmpty()[m
[32m+[m[32m    fun createUser(userInfo: User) {[m
[32m+[m[32m        Log.d("currentID", "${getCurrentUserId()}")[m
         val currentID = getCurrentUserId()[m
[31m-        if(!currentID.isNullOrEmpty()){[m
[32m+[m[32m        if (!currentID.isNullOrEmpty()) {[m
             mFirestore.collection(USER_COLLECTION)[m
                 .document(getCurrentUserId())[m
                 .set(userInfo, SetOptions.merge())[m
                 .addOnSuccessListener {[m
[31m-                    Log.d("created","")[m
[32m+[m[32m                    Log.d("created", "")[m
                 }[m
                 .addOnFailureListener {[m
[31m-                    Log.d("created fail","")[m
[32m+[m[32m                    Log.d("created fail", "")[m
                 }[m
         }[m
 [m
     }[m
[31m-    fun registerUser(name: String, email: String, password: String)  {[m
[32m+[m
[32m+[m
[32m+[m[32m    fun registerUser(name: String, email: String, password: String) {[m
         val name = name.trim { it <= ' ' }[m
         val email = email.trim { it <= ' ' }[m
         val password = password.trim { it <= ' ' }[m
 [m
[31m-[m
         firebase[m
             .createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->[m
[31m-                if(task.isSuccessful){[m
[32m+[m[32m                if (task.isSuccessful) {[m
                     val firebaseUser: FirebaseUser = task.result!!.user!![m
                     val registeredEmail = firebaseUser.email!![m
[31m-                    Log.d("Register successful","email: $email")[m
[31m-                    val user = User(id= firebaseUser.uid,email =registeredEmail,name=name)[m
[32m+[m[32m                    Log.d("Register successful", "email: $email")[m
[32m+[m[32m                    val user = User(id = firebaseUser.uid, email = registeredEmail, name = name)[m
                     createUser(user)[m
[31m-[m
                 } else {[m
[31m-                    Log.d("Register error","")[m
[31m-[m
[32m+[m[32m                    Log.d("Register error", "")[m
                 }[m
             }[m
     }[m
 [m
[31m-    fun getUser(userid : String){[m
[32m+[m[32m    fun getUserById(userId: String, callback: (User?) -> Unit) {[m
         mFirestore.collection(USER_COLLECTION)[m
[31m-            .document(userid)[m
[32m+[m[32m            .document(userId)[m
             .get()[m
             .addOnSuccessListener { userDocument ->[m
[31m-                val loggedinUser  = userDocument.toObject(User::class.java)[m
[31m-                Log.d("Logged In","")[m
[32m+[m[32m                val userReturned = userDocument.toObject(User::class.java)[m
[32m+[m[32m                callback(userReturned)[m
[32m+[m[32m                Log.d("Logged In", "")[m
[32m+[m[32m                Log.d("user name", "${userReturned?.name}")[m
[32m+[m[32m                Log.d("user name", "${userReturned?.email}")[m
[32m+[m[32m                Log.d("user name", "${userReturned?.id}")[m
             }[m
             .addOnFailureListener {[m
[31m-                Log.d("Log in fail","")[m
[32m+[m[32m                Log.d("Log in fail", "")[m
[32m+[m[32m                callback(null)[m
             }[m
[31m-[m
     }[m
 [m
[31m-    fun signInRegisteredUser(email: String,password: String){[m
[32m+[m[32m    fun signInRegisteredUser(email: String, password: String) {[m
         val email = email.trim { it <= ' ' }[m
         val password = password.trim { it <= ' ' }[m
 [m
         firebase[m
             .signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->[m
[31m-                if(task.isSuccessful){[m
[32m+[m[32m                if (task.isSuccessful) {[m
                     val user = firebase.currentUser[m
[31m-                    Log.d("Sign in successful","email: $email")[m
[31m-                    Log.d("Sign in successful","password: $password")[m
[31m-                    getUser(user?.uid.toString())[m
[32m+[m[32m                    Log.d("Sign in successful", "email: $email")[m
[32m+[m[32m                    Log.d("Sign in successful", "password: $password")[m
[32m+[m[32m                    //getUser(user?.uid.toString())[m
                 } else {[m
[31m-                    Log.d("sign in error","")[m
[32m+[m[32m                    Log.d("sign in error", "")[m
                 }[m
             }[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m
 [m
[32m+[m[32m    fun update(name: String, email: String, mobile: String, callback: (Boolean) -> Unit) {[m
[32m+[m[32m        val id = getCurrentUserId()[m
[32m+[m[32m        mFirestore.collection(USER_COLLECTION)[m
[32m+[m[32m            .document(id)[m
[32m+[m[32m            .update([m
[32m+[m[32m                "name", name,[m
[32m+[m[32m                "email", email,[m
[32m+[m[32m                "mobile", mobile.toLong()[m
[32m+[m[32m            )[m
[32m+[m[32m            .addOnSuccessListener {[m
[32m+[m[32m                Log.d("Update user", "User ${id} updated successfully")[m
[32m+[m[32m                callback(true)[m
[32m+[m[32m            }[m
[32m+[m[32m            .addOnFailureListener {[m
[32m+[m[32m                Log.d("Update user", "Error updating user ${id}", it)[m
[32m+[m[32m                callback(false)[m
[32m+[m[32m            }[m
     }[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/utils/Validation/state/UserState.kt b/app/src/main/java/com/example/projectmanager/utils/Validation/state/UserState.kt[m
[1mindex 67245df..63a4139 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/utils/Validation/state/UserState.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/utils/Validation/state/UserState.kt[m
[36m@@ -1,6 +1,9 @@[m
 package com.example.projectmanager.utils.Validation.state[m
 [m
[31m-sealed class UserState{[m
[31m-    object alreadyLogin :UserState()[m
[32m+[m[32mimport com.example.projectmanager.Models.User[m
[32m+[m[32mimport kotlinx.coroutines.flow.MutableStateFlow[m
[32m+[m
[32m+[m[32msealed class UserState {[m
[32m+[m[32m    data class alreadyLogin(val data: User) : UserState()[m
     object notYetLogin : UserState()[m
 }[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/view/ProfileScreen.kt b/app/src/main/java/com/example/projectmanager/view/ProfileScreen.kt[m
[1mindex 811a25c..e2ff9c0 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/view/ProfileScreen.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/view/ProfileScreen.kt[m
[36m@@ -1,4 +1,146 @@[m
 package com.example.projectmanager.view[m
 [m
[31m-class ProfileScreen {[m
[31m-}[m
\ No newline at end of file[m
[32m+[m[32mimport android.annotation.SuppressLint[m
[32m+[m[32mimport android.util.Log[m
[32m+[m[32mimport androidx.compose.foundation.Image[m
[32m+[m[32mimport androidx.compose.foundation.background[m
[32m+[m[32mimport androidx.compose.foundation.border[m
[32m+[m[32mimport androidx.compose.foundation.layout.*[m
[32m+[m[32mimport androidx.compose.foundation.shape.CircleShape[m
[32m+[m[32mimport androidx.compose.material.Card[m
[32m+[m[32mimport androidx.compose.material.Scaffold[m
[32m+[m[32mimport androidx.compose.material.ScaffoldState[m
[32m+[m[32mimport androidx.compose.material.rememberScaffoldState[m
[32m+[m[32mimport androidx.compose.runtime.Composable[m
[32m+[m[32mimport androidx.compose.runtime.rememberCoroutineScope[m
[32m+[m[32mimport androidx.compose.ui.Alignment[m
[32m+[m[32mimport androidx.compose.ui.Modifier[m
[32m+[m[32mimport androidx.compose.ui.draw.clip[m
[32m+[m[32mimport androidx.compose.ui.graphics.Color[m
[32m+[m[32mimport androidx.compose.ui.layout.ContentScale[m
[32m+[m[32mimport androidx.compose.ui.platform.LocalContext[m
[32m+[m[32mimport androidx.compose.ui.res.painterResource[m
[32m+[m[32mimport androidx.compose.ui.text.input.KeyboardType[m
[32m+[m[32mimport androidx.compose.ui.text.input.PasswordVisualTransformation[m
[32m+[m[32mimport androidx.compose.ui.tooling.preview.Preview[m
[32m+[m[32mimport androidx.compose.ui.unit.dp[m
[32m+[m[32mimport coil.compose.rememberImagePainter[m
[32m+[m[32mimport com.example.projectmanager.R[m
[32m+[m[32mimport com.example.projectmanager.component.InformationField[m
[32m+[m[32mimport com.example.projectmanager.component.TopBar[m
[32m+[m[32mimport com.example.projectmanager.component.primaryButton[m
[32m+[m[32mimport com.example.projectmanager.navigation.MainActions[m
[32m+[m[32mimport com.example.projectmanager.repository.AuthRepository[m
[32m+[m[32mimport com.example.projectmanager.utils.Validation.event.ValidationEvent[m
[32m+[m[32mimport com.example.projectmanager.viewmodel.UserViewModel[m
[32m+[m[32mimport com.google.firebase.firestore.auth.User[m
[32m+[m[32mimport kotlinx.coroutines.CoroutineScope[m
[32m+[m
[32m+[m[32m@SuppressLint("UnusedMaterialScaffoldPaddingParameter")[m
[32m+[m[32m@Composable[m
[32m+[m[32mfun ProfileScreen([m
[32m+[m[32m    actions: MainActions,[m
[32m+[m[32m    user: com.example.projectmanager.Models.User,[m
[32m+[m[32m    userViewModel: UserViewModel[m
[32m+[m[32m) {[m
[32m+[m
[32m+[m[32m    val coroutineScope: CoroutineScope = rememberCoroutineScope()[m
[32m+[m[32m    val scaffoldState: ScaffoldState = rememberScaffoldState()[m
[32m+[m[32m    val context = LocalContext.current[m
[32m+[m[32m    val repository: AuthRepository = AuthRepository()[m
[32m+[m[32m    Scaffold([m
[32m+[m[32m        scaffoldState = scaffoldState,[m
[32m+[m[32m        topBar = { TopBar(title = "My Profile", onClick = { actions.upPress() }) }) {[m
[32m+[m[32m        Box([m
[32m+[m[32m            modifier = Modifier[m
[32m+[m[32m                .fillMaxSize(),[m
[32m+[m[32m            contentAlignment = Alignment.Center[m
[32m+[m[32m        ) {[m
[32m+[m[32m            Image([m
[32m+[m[32m                modifier = Modifier[m
[32m+[m[32m                    .fillMaxSize(),[m
[32m+[m[32m                painter = painterResource(id = R.drawable.ic_background),[m
[32m+[m[32m                contentDescription = "",[m
[32m+[m[32m                contentScale = ContentScale.Crop[m
[32m+[m[32m            )[m
[32m+[m[32m            Column([m
[32m+[m[32m                horizontalAlignment = Alignment.CenterHorizontally,[m
[32m+[m[32m                modifier = Modifier[m
[32m+[m[32m                    .fillMaxWidth()[m
[32m+[m[32m            ) {[m
[32m+[m[32m                Card([m
[32m+[m[32m                    modifier = Modifier[m
[32m+[m[32m                        .padding(16.dp),[m
[32m+[m[32m                    elevation = 10.dp[m
[32m+[m[32m                ) {[m
[32m+[m[32m                    Spacer(modifier = Modifier.height(15.dp))[m
[32m+[m[32m                    Column([m
[32m+[m[32m                        modifier = Modifier.padding([m
[32m+[m[32m                            top = 25.dp,[m
[32m+[m[32m                            bottom = 25.dp,[m
[32m+[m[32m                            start = 15.dp,[m
[32m+[m[32m                            end = 15.dp[m
[32m+[m[32m                        ),[m
[32m+[m[32m                        horizontalAlignment = Alignment.CenterHorizontally[m
[32m+[m[32m                    ) {[m
[32m+[m[32m                        Box([m
[32m+[m[32m                            modifier = Modifier[m
[32m+[m[32m                                .size(100.dp)[m
[32m+[m[32m                                .clip(CircleShape)[m
[32m+[m[32m                                .border(2.dp, Color.White, CircleShape)[m
[32m+[m[32m                                .background(Color.Gray),[m
[32m+[m[32m                            contentAlignment = Alignment.Center[m
[32m+[m[32m                        ) {[m
[32m+[m[32m                            Image([m
[32m+[m[32m                                modifier = Modifier[m
[32m+[m[32m                                    .fillMaxSize()[m
[32m+[m[32m                                    .clip(CircleShape)[m
[32m+[m[32m                                    .padding(10.dp),[m
[32m+[m[32m                                painter = rememberImagePainter(data = user.image),[m
[32m+[m[32m                                contentDescription = null,[m
[32m+[m[32m                                contentScale = ContentScale.Crop[m
[32m+[m[32m                            )[m
[32m+[m[32m                        }[m
[32m+[m[32m                        var name: String = ""[m
[32m+[m[32m                        var email: String = ""[m
[32m+[m[32m                        var mobile: String = ""[m
[32m+[m
[32m+[m[32m                        InformationField([m
[32m+[m[32m                            label = "Name",[m
[32m+[m[32m                            KeyboardType.Email,[m
[32m+[m[32m                            onValueChange = {[m
[32m+[m[32m                                name = it[m
[32m+[m[32m                            },[m
[32m+[m[32m                            rememberTextValue = user.name[m
[32m+[m[32m                        )[m
[32m+[m[32m                        InformationField([m
[32m+[m[32m                            label = "Email",[m
[32m+[m[32m                            KeyboardType.Email,[m
[32m+[m[32m                            onValueChange = {[m
[32m+[m[32m                                email = it[m
[32m+[m[32m                            },[m
[32m+[m[32m                            rememberTextValue = user.email[m
[32m+[m[32m                        )[m
[32m+[m[32m                        InformationField([m
[32m+[m[32m                            label = "Mobile",[m
[32m+[m[32m                            KeyboardType.Phone,[m
[32m+[m[32m                            onValueChange = {[m
[32m+[m[32m                                mobile = it[m
[32m+[m[32m                            },[m
[32m+[m[32m                            rememberTextValue = if (user.mobile.toString() == "0") "" else user.mobile.toString()[m
[32m+[m[32m                        )[m
[32m+[m
[32m+[m[32m                        primaryButton(text = "UPDATE", modifier = Modifier[m
[32m+[m[32m                            .padding(top = 10.dp, start = 5.dp, end = 5.dp)[m
[32m+[m[32m                            .fillMaxWidth(),[m
[32m+[m[32m                            color = Color(0xFF0A80F5),[m
[32m+[m[32m                            onClick = {[m
[32m+[m[32m                                userViewModel.updateUser(name, email, mobile)[m
[32m+[m[32m                            })[m
[32m+[m[32m                    }[m
[32m+[m[32m                }[m
[32m+[m[32m            }[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[41m+[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/view/SignInScreen.kt b/app/src/main/java/com/example/projectmanager/view/SignInScreen.kt[m
[1mindex f0c21fc..17a882b 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/view/SignInScreen.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/view/SignInScreen.kt[m
[36m@@ -41,7 +41,7 @@[m [mfun SignInScreen(actions: MainActions, viewModel: BaseValidationViewModel) {[m
     val state = viewModel.state[m
     val context = LocalContext.current[m
     val repository: AuthRepository = AuthRepository()[m
[31m-    Scaffold(scaffoldState = scaffoldState,topBar = { TopBar(title = "SIGN IN", action = actions) }) {[m
[32m+[m[32m    Scaffold(scaffoldState = scaffoldState,topBar = { TopBar(title = "SIGN IN", onClick = { actions.upPress() }) }) {[m
         Box([m
             modifier = Modifier[m
                 .fillMaxSize(),[m
[36m@@ -106,8 +106,8 @@[m [mfun SignInScreen(actions: MainActions, viewModel: BaseValidationViewModel) {[m
                                     Log.d("error", "${state.passwordError}")[m
                                     showSignInError(viewModel, scaffoldState, coroutineScope)[m
                                 } else {[m
[32m+[m[32m                                    Log.d("sign in clicked","")[m
                                     signIn(viewModel = viewModel, context = context,repository)[m
[31m-[m
                                 }[m
                             })[m
                     }[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/view/SignUpScreen.kt b/app/src/main/java/com/example/projectmanager/view/SignUpScreen.kt[m
[1mindex b52827b..bf08dee 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/view/SignUpScreen.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/view/SignUpScreen.kt[m
[36m@@ -44,7 +44,7 @@[m [mfun SignUpScreen(actions: MainActions, viewModel: BaseValidationViewModel) {[m
     viewModel.type = "sign_up"[m
     Scaffold([m
         scaffoldState = scaffoldState,[m
[31m-        topBar = { TopBar(title = "SIGN UP", action = actions) }) {[m
[32m+[m[32m        topBar = { TopBar(title = "SIGN UP", onClick = { actions.upPress() }) }) {[m
         Box([m
             modifier = Modifier[m
                 .fillMaxSize(),[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/view/Splashscreen.kt b/app/src/main/java/com/example/projectmanager/view/Splashscreen.kt[m
[1mindex bac500c..e32ba8f 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/view/Splashscreen.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/view/Splashscreen.kt[m
[36m@@ -1,5 +1,7 @@[m
 package com.example.projectmanager.view[m
 [m
[32m+[m[32mimport android.annotation.SuppressLint[m
[32m+[m[32mimport android.util.Log[m
 import androidx.compose.foundation.Image[m
 import androidx.compose.foundation.layout.*[m
 import androidx.compose.material.Text[m
[36m@@ -18,22 +20,27 @@[m [mimport com.example.projectmanager.R[m
 import com.example.projectmanager.navigation.MainActions[m
 import com.example.projectmanager.repository.AuthRepository[m
 import com.example.projectmanager.ui.theme.typography[m
[32m+[m[32mimport com.example.projectmanager.utils.Validation.state.UserState[m
 import com.example.projectmanager.viewmodel.UserViewModel[m
[32m+[m[32mimport com.google.protobuf.Empty[m
 import kotlinx.coroutines.delay[m
 [m
 [m
[32m+[m[32m@SuppressLint("StateFlowValueCalledInComposition")[m
 @Composable[m
 fun SplashCreen(actions: MainActions, userViewModel: UserViewModel) {[m
[31m-    LaunchedEffect(key1 = true){[m
[32m+[m[32m    val curUserId = userViewModel.getCurUserId()[m
[32m+[m[32m    LaunchedEffect(key1 = true) {[m
         delay(3000)[m
[31m-        val currentUser = AuthRepository().getCurrentUserId()[m
[31m-        if(!currentUser.isNullOrEmpty()){[m
[32m+[m[32m        if (!curUserId.isNullOrEmpty()) {[m
             actions.gotoUser()[m
[31m-        }else{[m
[32m+[m[32m        } else {[m
             actions.gotoLogin()[m
         }[m
[31m-[m
     }[m
[32m+[m
[32m+[m
[32m+[m
     Box([m
         modifier = Modifier[m
             .fillMaxSize(),[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/view/UserScreen.kt b/app/src/main/java/com/example/projectmanager/view/UserScreen.kt[m
[1mindex a4b23ea..ac389ef 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/view/UserScreen.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/view/UserScreen.kt[m
[36m@@ -1,23 +1,75 @@[m
 package com.example.projectmanager.view[m
 [m
 import android.annotation.SuppressLint[m
[32m+[m[32mimport android.content.Context[m
[32m+[m[32mimport android.util.Log[m
[32m+[m[32mimport androidx.activity.ComponentActivity[m
 import androidx.compose.foundation.background[m
 import androidx.compose.foundation.layout.Box[m
 import androidx.compose.foundation.layout.fillMaxSize[m
 import androidx.compose.material.Scaffold[m
 import androidx.compose.material.Text[m
[32m+[m[32mimport androidx.compose.material.icons.Icons[m
[32m+[m[32mimport androidx.compose.material.icons.filled.Menu[m
[32m+[m[32mimport androidx.compose.material.rememberScaffoldState[m
 import androidx.compose.runtime.Composable[m
[32m+[m[32mimport androidx.compose.runtime.LaunchedEffect[m
[32m+[m[32mimport androidx.compose.runtime.rememberCoroutineScope[m
 import androidx.compose.ui.Modifier[m
 import androidx.compose.ui.graphics.Color[m
[32m+[m[32mimport androidx.compose.ui.platform.LocalContext[m
[32m+[m[32mimport com.example.projectmanager.component.NavigationDraw[m
[32m+[m[32mimport com.example.projectmanager.component.TopBar[m
[32m+[m[32mimport com.example.projectmanager.navigation.MainActions[m
[32m+[m[32mimport com.example.projectmanager.utils.Validation.state.UserState[m
[32m+[m[32mimport com.example.projectmanager.viewmodel.BaseValidationViewModel[m
[32m+[m[32mimport com.example.projectmanager.viewmodel.UserViewModel[m
[32m+[m[32mimport kotlinx.coroutines.launch[m
 [m
[31m-@SuppressLint("UnusedMaterialScaffoldPaddingParameter")[m
[32m+[m[32m@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")[m
 @Composable[m
[31m-fun UserScreen(){[m
[31m-    Scaffold(modifier = Modifier.fillMaxSize()) {[m
[31m-        Box(modifier = Modifier[m
[31m-            .fillMaxSize()[m
[31m-            .background(color = Color.White)){[m
[31m-            Text(text = "hello user")[m
[32m+[m[32mfun UserScreen([m
[32m+[m[32m    actions: MainActions,[m
[32m+[m[32m    viewModel: BaseValidationViewModel,[m
[32m+[m[32m    userViewModel: UserViewModel[m
[32m+[m[32m) {[m
[32m+[m
[32m+[m[32m    val result = userViewModel.user.value[m
[32m+[m[32m    Log.d("userscreen result","$result")[m
[32m+[m[32m    when(result){[m
[32m+[m[32m        is UserState.alreadyLogin ->{[m
[32m+[m[32m            val user = result.data[m
[32m+[m[32m            Log.d("user profile:","${user.name}")[m
[32m+[m[32m            val scaffoldState = rememberScaffoldState()[m
[32m+[m[32m            val scope = rememberCoroutineScope()[m
[32m+[m[32m            val context = LocalContext.current as ComponentActivity[m
[32m+[m[32m            Scaffold([m
[32m+[m[32m                scaffoldState = scaffoldState,[m
[32m+[m[32m                modifier = Modifier.fillMaxSize(),[m
[32m+[m[32m                drawerContent = {[m
[32m+[m[32m                    NavigationDraw(user,scaffoldState,userViewModel, actions,context )[m
[32m+[m[32m                },[m
[32m+[m[32m                topBar = {[m
[32m+[m[32m                    TopBar([m
[32m+[m[32m                        title = "Project Manager",[m
[32m+[m[32m                        onClick = { scope.launch { scaffoldState.drawerState.open() } },[m
[32m+[m[32m                        icon = Icons.Default.Menu[m
[32m+[m[32m                    )[m
[32m+[m[32m                },[m
[32m+[m[32m                drawerGesturesEnabled = scaffoldState.drawerState.isOpen[m
[32m+[m
[32m+[m[32m            ) {[m
[32m+[m[32m                Box([m
[32m+[m[32m                    modifier = Modifier[m
[32m+[m[32m                        .fillMaxSize()[m
[32m+[m[32m                        .background(color = Color.White)[m
[32m+[m[32m                ) {[m
[32m+[m[32m                    Text(text = "hello user")[m
[32m+[m[32m                }[m
[32m+[m[32m            }[m
[32m+[m[32m        }[m
[32m+[m[32m        else -> {[m
[32m+[m[32m            Text(text = "Somthing Error")[m
         }[m
     }[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/viewmodel/BaseValidationViewModel.kt b/app/src/main/java/com/example/projectmanager/viewmodel/BaseValidationViewModel.kt[m
[1mindex 6119418..428f670 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/viewmodel/BaseValidationViewModel.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/viewmodel/BaseValidationViewModel.kt[m
[36m@@ -54,9 +54,6 @@[m [mclass BaseValidationViewModel([m
     }[m
 [m
     fun submitData() {[m
[31m-[m
[31m-[m
[31m-[m
         //sign in validation[m
         if (type == "sign_in") {[m
             val emailResult: ValidationResult = validateEmail.execute(state.email)[m
[36m@@ -78,9 +75,7 @@[m [mclass BaseValidationViewModel([m
             viewModelScope.launch {[m
                 resultEventChannel.send(EventResult.Success)[m
             }[m
[31m-[m
         }[m
[31m-[m
         //sign up validation[m
         if (type == "sign_up") {[m
             val nameResult: ValidationResult = validateName.execute(state.name)[m
[36m@@ -110,8 +105,6 @@[m [mclass BaseValidationViewModel([m
             viewModelScope.launch {[m
                 resultEventChannel.send(EventResult.Success)[m
             }[m
[31m-[m
[31m-[m
         }[m
     }[m
 [m
[1mdiff --git a/app/src/main/java/com/example/projectmanager/viewmodel/UserViewModel.kt b/app/src/main/java/com/example/projectmanager/viewmodel/UserViewModel.kt[m
[1mindex 0dc56e2..ee4b26f 100644[m
[1m--- a/app/src/main/java/com/example/projectmanager/viewmodel/UserViewModel.kt[m
[1m+++ b/app/src/main/java/com/example/projectmanager/viewmodel/UserViewModel.kt[m
[36m@@ -1,13 +1,60 @@[m
 package com.example.projectmanager.viewmodel[m
 [m
[32m+[m[32mimport android.util.Log[m
 import androidx.lifecycle.ViewModel[m
[32m+[m[32mimport com.example.projectmanager.repository.AuthRepository[m
 import com.example.projectmanager.utils.Validation.state.UserState[m
[32m+[m[32mimport com.google.firebase.auth.FirebaseAuth[m
[32m+[m[32mimport com.google.firebase.firestore.FirebaseFirestore[m
[32m+[m[32mimport com.google.firebase.firestore.auth.User[m
[32m+[m[32mimport com.google.rpc.context.AttributeContext[m
 import kotlinx.coroutines.flow.MutableStateFlow[m
 import kotlinx.coroutines.flow.asStateFlow[m
 [m
[31m-class UserViewModel :ViewModel() {[m
[32m+[m[32mclass UserViewModel : ViewModel() {[m
 [m
[31m-    private val userState = MutableStateFlow<UserState>(UserState.notYetLogin)[m
[31m-    val user = userState.asStateFlow()[m
[32m+[m[32m    private val _userState = MutableStateFlow<UserState>(UserState.notYetLogin)[m
[32m+[m[32m    val user = _userState.asStateFlow()[m
[32m+[m
[32m+[m[32m    private val currentUserId = AuthRepository().getCurrentUserId()[m
[32m+[m[32m    fun getCurUserId(): String = currentUserId[m
[32m+[m
[32m+[m[32m    fun getcurrentUser() {[m
[32m+[m[32m        if (!currentUserId.isNullOrEmpty()) {[m
[32m+[m[32m            Log.d("curruseridUserViewModel", "$currentUserId")[m
[32m+[m[32m            AuthRepository().getUserById(currentUserId)[m
[32m+[m[32m            { user ->[m
[32m+[m[32m                Log.d("userViewModel", "${user?.name}")[m
[32m+[m[32m                if (user != null) {[m
[32m+[m[32m                    _userState.value = UserState.alreadyLogin(user)[m
[32m+[m[32m                    Log.d("user state:", "logged in ${user.name}")[m
[32m+[m[32m                } else {[m
[32m+[m[32m                    Log.d("user state:", "fail to get user")[m
[32m+[m[32m                }[m
[32m+[m[32m            }[m
[32m+[m[32m        } else {[m
[32m+[m[32m            Log.d("dont have user", "")[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    fun updateUser( name: String, email: String, mobile: String) {[m
[32m+[m[32m        AuthRepository().update(name, email, mobile){[m
[32m+[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    fun updateProfileImage(){[m
[32m+[m[32m        checkPremission()[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    private fun checkPremission() {[m
[32m+[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m
[32m+[m[32m    fun signOut() {[m
[32m+[m[32m        FirebaseAuth.getInstance().signOut()[m
[32m+[m[32m        _userState.value = UserState.notYetLogin[m
[32m+[m[32m    }[m
 }[m
 [m
[1mdiff --git a/app/src/main/res/drawable/ic_nav_menu.xml b/app/src/main/res/drawable/ic_nav_menu.xml[m
[1mindex fe51230..9ae405d 100644[m
[1m--- a/app/src/main/res/drawable/ic_nav_menu.xml[m
[1m+++ b/app/src/main/res/drawable/ic_nav_menu.xml[m
[36m@@ -1,5 +1,10 @@[m
[31m-<vector android:height="24dp" android:tint="#000000"[m
[31m-    android:viewportHeight="24" android:viewportWidth="24"[m
[31m-    android:width="24dp" xmlns:android="http://schemas.android.com/apk/res/android">[m
[31m-    <path android:fillColor="#FF000000" android:pathData="M17.6,11.48 L19.44,8.3a0.63,0.63 0,0 0,-1.09 -0.63l-1.88,3.24a11.43,11.43 0,0 0,-8.94 0L5.65,7.67a0.63,0.63 0,0 0,-1.09 0.63L6.4,11.48A10.81,10.81 0,0 0,1 20L23,20A10.81,10.81 0,0 0,17.6 11.48ZM7,17.25A1.25,1.25 0,1 1,8.25 16,1.25 1.25,0 0,1 7,17.25ZM17,17.25A1.25,1.25 0,1 1,18.25 16,1.25 1.25,0 0,1 17,17.25Z"/>[m
[31m-</vector>[m
[32m+[m[32m<vector xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32mandroid:width="24dp"[m
[32m+[m[32mandroid:height="24dp"[m
[32m+[m[32mandroid:viewportWidth="24"[m
[32m+[m[32mandroid:viewportHeight="24"[m
[32m+[m[32m>[m
[32m+[m[32m<path[m
[32m+[m[32m    android:fillColor="@android:color/white"[m
[32m+[m[32m    android:pathData="M3,18h18v-2L3,16v2zM3,13h18v-2L3,11v2zM3,6v2h18L21,6L3,6z"/>[m
[32m+[m[32m</vector>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/drawable/ic_user_placeholder.xml b/app/src/main/res/drawable/ic_user_placeholder.xml[m
[1mindex fe51230..3042d3f 100644[m
[1m--- a/app/src/main/res/drawable/ic_user_placeholder.xml[m
[1m+++ b/app/src/main/res/drawable/ic_user_placeholder.xml[m
[36m@@ -1,5 +1,10 @@[m
[31m-<vector android:height="24dp" android:tint="#000000"[m
[31m-    android:viewportHeight="24" android:viewportWidth="24"[m
[31m-    android:width="24dp" xmlns:android="http://schemas.android.com/apk/res/android">[m
[31m-    <path android:fillColor="#FF000000" android:pathData="M17.6,11.48 L19.44,8.3a0.63,0.63 0,0 0,-1.09 -0.63l-1.88,3.24a11.43,11.43 0,0 0,-8.94 0L5.65,7.67a0.63,0.63 0,0 0,-1.09 0.63L6.4,11.48A10.81,10.81 0,0 0,1 20L23,20A10.81,10.81 0,0 0,17.6 11.48ZM7,17.25A1.25,1.25 0,1 1,8.25 16,1.25 1.25,0 0,1 7,17.25ZM17,17.25A1.25,1.25 0,1 1,18.25 16,1.25 1.25,0 0,1 17,17.25Z"/>[m
[32m+[m[32m<vector xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m    android:width="24dp"[m
[32m+[m[32m    android:height="24dp"[m
[32m+[m[32m    android:viewportWidth="24"[m
[32m+[m[32m    android:viewportHeight="24"[m
[32m+[m[32m    >[m
[32m+[m[32m    <path[m
[32m+[m[32m        android:fillColor="@android:color/white"[m
[32m+[m[32m        android:pathData="M12,12c2.21,0 4,-1.79 4,-4s-1.79,-4 -4,-4 -4,1.79 -4,4 1.79,4 4,4zM12,14c-2.67,0 -8,1.34 -8,4v2h16v-2c0,-2.66 -5.33,-4 -8,-4z"/>[m
 </vector>[m
