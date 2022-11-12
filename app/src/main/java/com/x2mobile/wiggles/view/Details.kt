/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.x2mobile.wiggles.view

import android.annotation.SuppressLint
import android.icu.text.CaseMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.x2mobile.wiggles.R
import com.x2mobile.wiggles.component.DogInfoCard
import com.x2mobile.wiggles.component.InfoCard
import com.x2mobile.wiggles.component.OwnerCard
import com.x2mobile.wiggles.data.FakeDogDatabase
import com.x2mobile.wiggles.model.Dog

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Details(navController: NavController, id: Int) {
    val dog = FakeDogDatabase.dogList[id]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = colorResource(id = R.color.text),
                navigationIcon = { TopAppBarNavIcon(navController::navigateUp) }
            )
        },

        content = {
            DetailsView(dog)
        }
    )
}

@Composable
fun TopAppBarNavIcon(onClick: () -> Unit) {

}

@Composable
fun DetailsView(dog: Dog) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
    ) {
        // Basic details
        item {
            BasicDetail(dogImageId = dog.image, name = dog.name, gender = dog.gender, location = dog.location)
        }

        // My story details
        item {
            MyStoryDetails(about = dog.about)
        }

        // Quick info
        item {
            QuickInfo(age = dog.age, color = dog.color, weight = dog.weight)
        }

        // Owner info
        item {
            OwnerInfo(name = dog.owner.name, bio = dog.owner.bio, image = dog.owner.image)
        }

        // CTA - Adopt me button
        item {
            AdoptMeButton()
        }
    }
}

@Composable
fun BasicDetail(dogImageId: Int, name: String, gender: String, location: String) {
    val dogImage: Painter = painterResource(id = dogImageId)
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(346.dp),
        painter = dogImage,
        alignment = Alignment.CenterStart,
        contentDescription = "",
        contentScale = ContentScale.Crop
    )

    Spacer(modifier = Modifier.height(16.dp))

    DogInfoCard(name, gender, location)
}

@Composable
fun MyStoryDetails(about: String) {

}

@Composable
fun QuickInfo(age: Double, color: String, weight: Double) {
    Spacer(modifier = Modifier.height(24.dp))
    Title(title = "Dog info")
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InfoCard(title = "Age", value = age.toString().plus(" yrs"))
        InfoCard(title = "Color", value = color)
        InfoCard(title = "Weight", value = weight.toString().plus("Kg"))
    }
}

@Composable
fun OwnerInfo(name: String, bio: String, image: Int) {

}

@Composable
fun AdoptMeButton() {

}

@Composable
fun Title(title: String) {

}
