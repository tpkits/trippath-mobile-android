package com.sejun2.trippath.presentation.ui.component.dialog

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.data.model.OauthProvider
import com.sejun2.trippath.feature.auth.R
import com.sejun2.trippath.presentation.ui.component.SocialLoginButton
import com.sejun2.trippath.presentation.ui.model.toSocialLoginButtonModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginBottomSheetDialog(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    loginWithOauth: (OauthProvider, Context) -> Unit,
    onVisibilityChanged: (Boolean) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    if (isVisible) {
        ModalBottomSheet(
            modifier = modifier,
            sheetState = sheetState,
            onDismissRequest = {
                onVisibilityChanged(false)
            },
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    stringResource(R.string.auth_login_prompt),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(vertical = 16.dp))

                val context = LocalContext.current
                OauthProvider.entries.toTypedArray().forEach { provider ->
                    val uiModel = provider.toSocialLoginButtonModel()
                    SocialLoginButton(
                        label = uiModel.label,
                        backgroundColor = uiModel.backgroundColor,
                        borderColor = uiModel.borderColor,
                        iconRes = uiModel.iconRes,
                        onClick = { loginWithOauth(provider, context) }
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 12.dp))
            }
        }
    }
}
