package com.example.whatsappclone.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whatsappclone.R
import com.example.whatsappclone.navigation.Screens
import com.example.whatsappclone.viewmodel.auth.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    context: Context = LocalContext.current,
    navController: NavController = rememberNavController(),
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state = viewModel.signUpState.collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    var name by rememberSaveable { mutableStateOf("") }
    var number by rememberSaveable { mutableStateOf("") }
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
                    text = "Register",
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
                        text = "Name",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500, color = Color.Gray,
                        fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
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
                                modifier = Modifier.padding(10.dp), tint = Color.Gray
                            )
                        },
                        label = {
                            Text(
                                text = "Type your name", fontSize = 16.sp,
                                fontWeight = W600, color = Color.Gray,
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
                        text = "Number",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500, color = Color.Gray,
                        fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
                    )
                    OutlinedTextField(
                        value = number,
                        onValueChange = { number = it },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Gray,
                            unfocusedBorderColor = Gray,
                            focusedTextColor = Gray,
                            unfocusedTextColor = Gray
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Create,
                                contentDescription = "",
                                modifier = Modifier.padding(10.dp), tint = Color.Gray
                            )
                        },
                        label = {
                            Text(
                                text = "Type your number", fontSize = 16.sp,
                                fontWeight = W600, color = Color.Gray,
                                fontFamily = FontFamily.SansSerif
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))

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
                                imageVector = Icons.Outlined.AccountBox,
                                contentDescription = "",
                                modifier = Modifier.padding(10.dp), tint = Gray
                            )
                        },
                        label = {
                            Text(
                                text = "Type your email", fontSize = 16.sp,
                                fontWeight = W600, color = Color.Gray,
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
                                fontWeight = W600, color = Color.Gray,
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

                    Spacer(modifier = Modifier.padding(top = 50.dp))

                    Button(
                        onClick = {

                            if (name.isEmpty() || number.isEmpty() || email.isEmpty() || password.isEmpty()) {
                                Toast.makeText(context, "Enter all fields", Toast.LENGTH_SHORT)
                                    .show()
                                return@Button
                            } else {
                                scope.launch {
                                    viewModel.registerUser(email, password)
                                }
                            }

                        },
                        modifier = Modifier, colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3AD8A8),
                        )
                    ) {
                        Text(
                            text = "REGISTER",
                            fontSize = 20.sp, color = Color.Gray,
                            fontWeight = W600, textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    if (state.value?.isLoading == true) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(top = 100.dp))
                        Text(
                            text = "Already have an account",
                            fontSize = 18.sp, fontWeight = W600,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        Text(
                            text = "Login",
                            fontSize = 18.sp, fontWeight = FontWeight.W900,
                            color = Color(0xFF3AD8A8), modifier = Modifier.clickable {
                                navController.navigate(Screens.Login.route)
                            }
                        )

                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context, "You have successfully registered", Toast.LENGTH_SHORT).show()
                navController.navigate(Screens.Login.route)
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