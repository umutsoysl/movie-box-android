package com.umut.soysal.mobile.moviebox.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.umut.soysal.mobile.moviebox.R
import com.umut.soysal.mobile.moviebox.core.constant.GlobalConstant
import com.umut.soysal.mobile.moviebox.core.ui.theme.RatingBgColor
import com.umut.soysal.mobile.moviebox.core.ui.theme.Shapes
import com.umut.soysal.mobile.moviebox.data.remote.model.MovieModel

@Composable
fun MoviePosterComponent(movie: MovieModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(5.dp)
            .clickable(
                onClick = {
                    // go detail
                }
            ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val painter = rememberImagePainter(data = "${GlobalConstant.SERVER_IMAGE_URL}${movie.posterPath}",
             builder = {
                 placeholder(R.drawable.image_place_holder)
             })
            Box{
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(5.dp)
                        .clip(Shapes.medium),
                    painter = painter,
                    contentDescription = movie.title!!,
                    contentScale = ContentScale.FillWidth
                )
                Box(modifier = Modifier.padding(start = 11.dp, top = 10.dp)
                    .clip(Shapes.medium)
                    .background(color = RatingBgColor),
                   ) {
                    Text(modifier = Modifier.padding(3.dp)
                        ,text = movie.voteAverage!!.toString(), fontSize = 10.sp, fontFamily = FontFamily.SansSerif, color = Color.White)
                }
            }
            Text(text = movie.title!!, fontSize = 12.sp, fontFamily = FontFamily.SansSerif)
        }
    }
}