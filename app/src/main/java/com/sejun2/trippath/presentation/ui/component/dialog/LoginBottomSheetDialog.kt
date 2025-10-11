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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sejun2.trippath.domain.model.OauthProvider
import com.sejun2.trippath.presentation.ui.component.SocialLoginButton

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
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    "로그인하여\n나만의 여행을 계획하세요.",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(vertical = 16.dp))

                OauthProvider.entries.toTypedArray().indices.forEach { provider ->
                    SocialLoginButton(
                        provider = OauthProvider.entries[provider],
                        oauthLogin = loginWithOauth
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 12.dp))
            }
        }
    }
}
