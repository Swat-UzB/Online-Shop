package com.example.onlineshop.common.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshop.R
import com.example.onlineshop.profile.CommonMontserratText
import com.example.onlineshop.common.presentation.theme.montserrats

/**
 * Created by Zayniddinov Ilyosjon on 14/03/2023.
 *  email zayniddinovilyos@gmail.com.
 */
@Preview(showBackground = true)
@Composable
fun DefaultView() {
    var firstName by remember { mutableStateOf("Hello") }
    CustomTextField(value = firstName, onValueChange = { firstName = it })
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape =
        MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors()
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    @OptIn(ExperimentalMaterialApi::class)
    (BasicTextField(
        value = value,
        modifier = modifier
            .background(colors.backgroundColor(enabled).value, shape)
            .indicatorLine(enabled, false, interactionSource, colors)
            .defaultMinSize(TextFieldDefaults.MinWidth, 28.dp),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            // places leading icon, text field with label and placeholder, trailing icon
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            )
        }
    ))
}

@Composable
fun EditField(
    @StringRes placeholder: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    @StringRes errorMsg: Int? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    ),
) {
    val error = isError && value.isNotBlank()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        CustomTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = error,
            colors = colors,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            placeholder = {
                val margin = if (trailingIcon == null) 0.dp else 36.dp
                Text(
                    text = stringResource(id = placeholder),
                    modifier = Modifier
                        .padding(start = margin)
                        .fillMaxWidth(),
                    fontWeight = FontWeight.W500,
                    fontFamily = montserrats,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                )
            },
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 16.dp)
                .clip(CircleShape)
        )
        if (error && errorMsg != null) {
            CommonMontserratText(
                text = errorMsg,
                color = colorResource(id = R.color.error_color),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun CommonButton(
    @StringRes butText: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = butText),
            color = Color.White,
            fontWeight = FontWeight.W700,
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}
