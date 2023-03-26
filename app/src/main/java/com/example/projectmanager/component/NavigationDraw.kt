package com.example.projectmanager.component

import android.content.ComponentName
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.projectmanager.Models.MenuItem
import com.example.projectmanager.Models.User
import com.example.projectmanager.R
import com.example.projectmanager.navigation.MainActions
import com.example.projectmanager.navigation.Screen
import com.example.projectmanager.viewmodel.UserViewModel
import kotlinx.coroutines.launch


@Composable
fun NavigationDraw(
    user: User,
    scaffoldState: ScaffoldState,
    userviewModel: UserViewModel,
    actions: MainActions,
    context: ComponentActivity,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DrawerHeader(user.name, user.image)
        DrawerBody(
            items = listOf(
                MenuItem(
                    id = "Profile",
                    title = "My Profile",
                    icon = painterResource(id = R.drawable.ic_nav_user)
                ),
                MenuItem(
                    id = "Signout",
                    title = "Sign Out",
                    icon = painterResource(id = R.drawable.ic_nav_sign_out)
                )

            ),
            onMenuItemClick = { item ->
                when (item.id) {
                    "Profile" -> {
                        actions.gotoUserProfile()
                    }
                    "Signout" -> {
                        userviewModel.signOut()
                        //context.finish()
                        actions.gotoLogin()
                    }
                }
            },
            scaffoldState = scaffoldState
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DrawerHeader(userName: String, imageURL: String = "") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(Color(0xFF0A80F5))
            .padding(horizontal = 30.dp, vertical = 40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize()
                    .clip(CircleShape),
                painter = rememberImagePainter(data = if (imageURL.isNullOrEmpty()) R.drawable.ic_user_placeholder else imageURL),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier.size(100.dp),
            text = userName, fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onMenuItemClick: (MenuItem) -> Unit,
    scaffoldState: ScaffoldState
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onMenuItemClick(item)
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                    .padding(16.dp)
            )
            {
                Icon(painter = item.icon, contentDescription = "")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title, modifier = Modifier.weight(1f))
            }
        }
    }
}

