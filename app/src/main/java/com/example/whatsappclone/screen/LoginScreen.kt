package com.example.whatsappclone.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whatsappclone.R
import com.example.whatsappclone.navigation.Screens
import com.example.whatsappclone.viewmodel.auth.SignInViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    context: Context = LocalContext.current,
    navController: NavController = rememberNavController(),
    viewModel: SignInViewModel = hiltViewModel()
) {

    val state = viewModel.signInState.collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Image(
                    painter = painterResource(id = R.drawable.whatsapp),
                    contentDescription = "",
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(text = "Sign up", fontSize = 40.sp, fontWeight = FontWeight.W600)
                Spacer(modifier = Modifier.padding(top = 20.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("password") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Button(onClick = {
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(context, "Enter all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    else {
                        scope.launch {
                            viewModel.loginUser(email, password)
                        }
                    }


                }) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                if (state.value?.isLoading == true) {
                    CircularProgressIndicator()
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    text = "Not have an account?",
                    fontWeight = FontWeight.W400,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Button(onClick = {
                    navController.navigate(Screens.Signup.route)
                }) {
                    Text(text = "SignUp")
                }
                Spacer(modifier = Modifier.padding(top = 20.dp))


            }
        }
    }
    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotEmpty() == true) {
                val error = state.value?.isError
                Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
