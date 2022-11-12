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
package com.x2mobile.wiggles.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.x2mobile.wiggles.R
import com.x2mobile.wiggles.model.Dog

@Composable
fun ItemDogCard(dog: Dog, onItemClicked: (dog: Dog) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClicked(dog) }),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        ItemDogCardContent(dog = dog)
    }
}

@Composable
fun ItemDogCardContent(dog: Dog) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        DogImage(imageId = dog.image)

        DogInfo(name = dog.name, age = dog.age, gender = dog.gender, location = dog.location)

        DogGender(gender = dog.gender)
    }
}

@Composable
fun DogImage(imageId: Int) {
    val image: Painter = painterResource(id = imageId)

    Image(
        modifier = Modifier
            .size(80.dp, 80.dp)
            .clip(RoundedCornerShape(16.dp)),
        painter = image,
        alignment = Alignment.CenterStart,
        contentDescription = "",
        contentScale = ContentScale.Crop
    )

    Spacer(modifier = Modifier.width(16.dp))
}

@Composable
fun RowScope.DogInfo(name: String, age: Double, gender: String, location: String) {
    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
        Text(
            text = name,
            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
            color = MaterialTheme.colors.surface,
            fontWeight = FontWeight.Bold,
            style = typography.subtitle1
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = buildString {
                append(age)
                append("yrs | ")
                append(gender)
            },
            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
            color = MaterialTheme.colors.surface,
            style = typography.caption
        )

        Row(verticalAlignment = Alignment.Bottom) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                modifier = Modifier.size(16.dp, 16.dp),
                tint = Color.Red
            )

            Text(
                text = location,
                modifier = Modifier.padding(8.dp, 12.dp, 12.dp, 0.dp),
                color = MaterialTheme.colors.surface,
                style = typography.caption
            )
        }
    }
}

@Composable
fun DogGender(gender: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        GenderTag(gender)
    }
}
