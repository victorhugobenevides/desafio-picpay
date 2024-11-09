import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.ui.theme.colorAlert
import com.picpay.desafio.android.ui.theme.colorDetail
import com.picpay.desafio.android.ui.theme.colorPrimaryLight
import com.picpay.desafio.android.ui.theme.typography

@Composable
fun UserListItem(
    user: User,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ProfileImage(url = user.img)

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = user.username,
                fontWeight = FontWeight.Bold,
                color = colorPrimaryLight,
                fontSize = typography.bodyLarge.fontSize
            )
            Text(
                text = user.name,
                color = colorDetail,
                fontSize = typography.bodyMedium.fontSize
            )
        }
    }
}

@Composable
fun ProfileImage(url: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .testTag("ProfileImage")
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .testTag("Loading")
    ) {
        CircularProgressIndicator(color = colorPrimaryLight)
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit = {}) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("Error")
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.error, message),
                color = colorAlert,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

