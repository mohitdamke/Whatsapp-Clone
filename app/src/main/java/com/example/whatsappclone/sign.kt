package com.example.whatsappclone

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sign() {
    val focusManager = LocalFocusManager.current

    var title by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 60.dp))
        Text(
            text = "Login",
            fontSize = 40.sp,
            fontWeight = W900,
            fontFamily = FontFamily.SansSerif,
            color = Black
        )

        Spacer(modifier = Modifier.padding(top = 60.dp))
        Column(
            modifier = Modifier.padding(30.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Username",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500, color = Gray,
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
            )
            OutlinedTextField(value = title,
                onValueChange = { title = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Gray,
                    unfocusedBorderColor = Gray,
                    focusedTextColor = Gray,
                    unfocusedTextColor = Gray
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "",
                        modifier = Modifier.padding(10.dp), tint = Gray
                    )
                },
                label = {
                    Text(
                        text = "Type your username", fontSize = 16.sp,
                        fontWeight = W600, color = Gray,
                        fontFamily = FontFamily.SansSerif
                    )
                })
            Text(
                text = "Forgot password?",
                fontSize = 16.sp,
                fontWeight = W600,
                color = Gray,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(start = 180.dp)
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier, colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3AD8A8),
                )
            ) {
                Text(
                    text = "LOGIN",
                    fontSize = 20.sp, color = Gray,
                    fontWeight = W600, textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 40.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Or Sign Up Using",
                    fontSize = 18.sp, fontWeight = W600,
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
                        modifier = Modifier.size(40.dp), tint = Blue
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp),
                    )

                }
                Spacer(modifier = Modifier.padding(top = 100.dp))
                Text(
                    text = "Or Sign Up Using",
                    fontSize = 18.sp, fontWeight = W600,
                    color = Gray
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    text = "Sign UP",
                    fontSize = 18.sp, fontWeight = W900,
                    color = Gray, modifier = Modifier.clickable { }
                )
            }
        }


    }


}































