package com.example.onlineshop.home.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshop.R
import com.example.onlineshop.common.presentation.EditField
import com.example.onlineshop.common.presentation.model.UiFlashSaleProduct
import com.example.onlineshop.common.presentation.model.UiLatestProduct
import com.example.onlineshop.common.presentation.theme.OnlineShopTheme
import com.example.onlineshop.common.presentation.theme.poppins
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import java.text.NumberFormat

/**
 * Created by Zayniddinov Ilyosjon on 17/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */

@Composable
fun Page1MainScreen(
    state: HomeViewState
) {
    var search by remember { mutableStateOf("") }
    val focusManger = LocalFocusManager.current
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier
                .fillMaxSize()
                .padding(end = 36.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CommonPoppinsText(
                stringResource(id = R.string.location),
                fontWeight = FontWeight.W400,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.width(2.dp))
            Image(Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
        EditField(search, { search = it }, R.string.search_placeholder,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { focusManger.clearFocus() }),
            trailingIcon = {
                IconButton(onClick = {
                    if (search.isNotBlank()) {
                        search = ""
                    }
                })
                {
                    if (search.isBlank()) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    } else {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent, disabledIndicatorColor = Color.Transparent,
                backgroundColor = Color.LightGray.copy(alpha = 0.2f)),
            modifier = Modifier.padding(horizontal = 32.dp))
        Row(modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) { Category(icon = R.drawable.iphone_3, label = R.string.phones)
            Category(icon = R.drawable.headphone_2, label = R.string.headphones)
            Category(icon = R.drawable.games, label = R.string.games)
            Category(icon = R.drawable.cars_1, label = R.string.cars)
            Category(icon = R.drawable.furniture_5, label = R.string.furniture)
            Category(icon = R.drawable.kids_4, label = R.string.kids) }

        Spacer(modifier = Modifier.height(16.dp))
        ListName(listName = R.string.latest)
        LazyRow { items(state.latestList) { LatestItem(it) } }
        ListName(listName = R.string.flash_sale)
        LazyRow { items(state.flashSaleList) { FlashSaleItem(it) } }
        ListName(listName = R.string.brands)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewPage1() {
    OnlineShopTheme {
        Page1MainScreen(HomeViewState())
    }
}

@Preview(showBackground = true)
@Composable
fun LatestItemPreview() {
    OnlineShopTheme {
        LatestItem(
            UiLatestProduct(
                "Phones",
                "https://www.dhresource.com/0x0/f2/albu/g8/M01/9D/19/rBVaV1079WeAEW-AAARn9m_Dmh0487.jpg",
                "Samsung S10",
                1000f
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FlashSaleItemPreview() {
    OnlineShopTheme {
        FlashSaleItem(
            UiFlashSaleProduct(
                "Phones",
                "https://www.dhresource.com/0x0/f2/albu/g8/M01/9D/19/rBVaV1079WeAEW-AAARn9m_Dmh0487.jpg",
                "Samsung S10",
                30,
                1000f
            )
        )
    }
}

@Composable
fun LatestItem(
    uiLatestProduct: UiLatestProduct, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 130.dp, height = 150.dp)
            .padding(start = 12.dp)
    ) {
        CommonGlideImage(imageUrl = uiLatestProduct.imageUrl)
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            Surface(
                color = colorResource(id = R.color.category_back_color).copy(alpha = 0.85f),
                shape = MaterialTheme.shapes.large
            ) {
                CommonPoppinsText(
                    fontSize = 8.sp,
                    text = uiLatestProduct.category,
                    fontWeight = FontWeight.W600,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }

            CommonPoppinsText(
                text = uiLatestProduct.name,
                color = Color.White,
                maxLines = 2,
                fontSize = 10.sp,
                fontWeight = FontWeight.W600
            )
            CommonPoppinsText(
                text = "$ ${NumberFormat.getCurrencyInstance().format(uiLatestProduct.price)}",
                color = Color.White,
                maxLines = 1,
                fontSize = 8.sp,
                fontWeight = FontWeight.W600
            )

        }
        Image(
            Icons.Default.Add,
            colorFilter = ColorFilter.tint(colorResource(id = R.color.icon_color)),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(4.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.category_back))
        )
    }
}


@Composable
fun FlashSaleItem(
    uiFlashSaleProduct: UiFlashSaleProduct, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 200.dp, height = 220.dp)
            .padding(start = 12.dp)
    ) {
        CommonGlideImage(imageUrl = uiFlashSaleProduct.imageUrl)
        Image(
            painterResource(id = R.drawable.icon_1),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape),
            contentDescription = null
        )
        Surface(
            color = Color.Red, shape = MaterialTheme.shapes.large, modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopEnd)
        ) {
            CommonPoppinsText(
                text = "${uiFlashSaleProduct.discount}% off",
                color = Color.White,
                fontWeight = FontWeight.W600,
                fontSize = 10.sp,
                modifier = Modifier.padding(8.dp, 2.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            Surface(
                color = colorResource(id = R.color.category_back_color).copy(alpha = 0.85f),
                shape = MaterialTheme.shapes.large
            ) {
                CommonPoppinsText(
                    text = uiFlashSaleProduct.category,
                    fontWeight = FontWeight.W600,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp)
                )
            }

            CommonPoppinsText(
                text = uiFlashSaleProduct.name,
                color = Color.White,
                maxLines = 2,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600
            )
            CommonPoppinsText(
                text = "$ ${NumberFormat.getCurrencyInstance().format(uiFlashSaleProduct.price)}",
                color = Color.White,
                maxLines = 1,
                fontSize = 10.sp,
                fontWeight = FontWeight.W600
            )

        }
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                Icons.Filled.FavoriteBorder,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.icon_color)),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.category_back))
                    .padding(6.dp)
            )
            Image(
                Icons.Default.Add, null,
                Modifier
                    .padding(4.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.category_back)),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.icon_color)),
            )
        }


    }
}

@Composable
fun ListName(
    @StringRes listName: Int, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CommonPoppinsText(
            stringResource(id = listName), Modifier.alignByBaseline(), FontWeight.W500, Color.Black,
        )
        CommonPoppinsText(
            stringResource(id = R.string.view_all), Modifier.alignByBaseline(),
            FontWeight.W500, colorResource(id = R.color.text_gray), 12.sp,
        )
    }
}


@Composable
fun Category(
    @DrawableRes icon: Int, @StringRes label: Int, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.category_back))
                .padding(8.dp),
        )

        CommonPoppinsText(
            text = stringResource(id = label),
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 10.sp
        )
    }
}

@Composable
fun CommonPoppinsText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight? = null,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        color = color,
        text = text,
        fontSize = fontSize,
        fontFamily = poppins,
        fontWeight = fontWeight,
        maxLines = maxLines,
        modifier = modifier
    )
}

@Composable
fun CommonGlideImage(
    imageUrl: String, modifier: Modifier = Modifier
) {
    GlideImage(previewPlaceholder = R.drawable.ic_launcher_background,
        modifier = modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.large),
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop, alignment = Alignment.Center
        ),
        component = rememberImageComponent {
            // shows a shimmering effect when loading an image.
            +ShimmerPlugin(
                baseColor = Color.LightGray, highlightColor = Color.Gray
            )
        },
        failure = {
            Text(text = "image request failed.")
        })
}