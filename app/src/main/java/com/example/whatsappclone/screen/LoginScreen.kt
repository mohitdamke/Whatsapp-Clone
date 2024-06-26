package com.example.whatsappclone.screen

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whatsappclone.R
import com.example.whatsappclone.constants.Constants.ServerClient
import com.example.whatsappclone.navigation.Screens
import com.example.whatsappclone.viewmodel.auth.SignInViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    context: Context = LocalContext.current,
    navController: NavController = rememberNavController(),
    viewModel: SignInViewModel = hiltViewModel()
) {


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }

    val state = viewModel.signInState.collectAsState(initial = null)
    val googleState = viewModel.googleState.value
    val scope = rememberCoroutineScope()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoapp),
                    contentDescription = "",
                    modifier = Modifier.size(250.dp)
                )

                Text(
                    text = "Login",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.W900,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xFF3AD8A8)
                )

                Column(
                    modifier = Modifier.padding(30.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Email",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500, color = Gray,
                        fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Gray,
                            unfocusedBorderColor = Gray,
                            focusedTextColor = Gray,
                            unfocusedTextColor = Gray
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = "",
                                modifier = Modifier.padding(10.dp), tint = Gray
                            )
                        },
                        label = {
                            Text(
                                text = "Type your email", fontSize = 16.sp,
                                fontWeight = FontWeight.W600, color = Gray,
                                fontFamily = FontFamily.SansSerif
                            )
                        },
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
                    Text(
                        text = "Password",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500, color = Gray,
                        fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Gray,
                            unfocusedBorderColor = Gray,
                            focusedTextColor = Gray,
                            unfocusedTextColor = Gray
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = "",
                                modifier = Modifier.padding(10.dp), tint = Gray
                            )
                        },
                        label = {
                            Text(
                                text = "Type your password", fontSize = 16.sp,
                                fontWeight = FontWeight.W600, color = Gray,
                                fontFamily = FontFamily.SansSerif
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    Button(
                        onClick = {
                            if (email.isEmpty() || password.isEmpty()) {
                                Toast.makeText(context, "Enter all fields", Toast.LENGTH_SHORT)
                                    .show()
                                return@Button
                            } else {
                                scope.launch {
                                    viewModel.loginUser(email, password)
                                    navController.navigate(Screens.ChatList.route)
                                }
                            }
                        },
                        modifier = Modifier, colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3AD8A8),
                        )
                    ) {
                        Text(
                            text = "LOGIN",
                            fontSize = 20.sp, color = Gray,
                            fontWeight = FontWeight.W600, textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    if (state.value?.isLoading == true) {
                        CircularProgressIndicator()
                    }
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Or Sign Up Using",
                            fontSize = 18.sp, fontWeight = FontWeight.W600,
                            color = Gray
                        )
                        Row(
                            modifier = Modifier.padding(30.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "",
                                modifier = Modifier.size(40.dp), tint = Color.Blue
                            )
                            Spacer(modifier = Modifier.width(10.dp))


                            Image(
                                painter = painterResource(id = R.drawable.google),
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable {
                                        val gso =
                                            GoogleSignInOptions
                                                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                                .requestIdToken(ServerClient)
                                                .requestEmail()
                                                .build()

                                        val mGoogleSignInClient =
                                            GoogleSignIn.getClient(context, gso)

                                        launcher.launch(mGoogleSignInClient.signInIntent)
                                    }
                                    .size(40.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        if (googleState.isLoading) {
                            CircularProgressIndicator()
                        }
                        Spacer(modifier = Modifier.padding(top = 20.dp))


                        Text(
                            text = "Or Sign Up Using",
                            fontSize = 18.sp, fontWeight = FontWeight.W600,
                            color = Gray
                        )
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        Text(
                            text = "Sign UP",
                            fontSize = 18.sp, fontWeight = FontWeight.W900,
                            color = Color(0xFF3AD8A8), modifier = Modifier.clickable {
                                navController.navigate(Screens.Signup.route)
                            })
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_SHORT).show()
                navController.navigate(Screens.ChatList.route)
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

    LaunchedEffect(key1 = googleState.isSuccess) {
        scope.launch {
            if (googleState.isSuccess != null) {
                Toast.makeText(
                    context,
                    "SignIn With Google Account is Success ",
                    Toast.LENGTH_SHORT
                ).show()
            navController.navigate(Screens.ChatList.route)

            }
        }
    }
    LaunchedEffect(key1 = googleState.isError) {
        scope.launch {
            if (googleState.isError == null) {
                Toast.makeText(
                    context,
                    "Failed to SignIn With Google Account",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}



