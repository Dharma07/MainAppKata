package com.training.findmyip.findmyip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.findmyip.R
import com.training.findmyip.data.Resource
import com.training.findmyip.model.FindMyIPResponse
import kotlinx.coroutines.launch

@Composable
fun FindMyIPScreen(viewModel: MyIPViewModel) {

    val scope = rememberCoroutineScope()

    val myIPDetails by viewModel.findMyIPResponse.observeAsState(
        initial = Resource.Empty()
    )

    Screen(myIPDetails) {
        scope.launch {
            viewModel.findMyIp()
        }
    }
}

@Composable
fun Screen(myIPDetails: Resource<FindMyIPResponse>, onFindApiClick: () -> Unit) {
    when (myIPDetails) {
        is Resource.Error -> {
            myIPDetails.message?.let { ErrorMessage(it) }
        }

        is Resource.Loading -> {
            ShowProgress()
        }

        is Resource.Success -> {
            myIPDetails.data?.let { ShowMyIPInfoView(it.ip.toString()) }
        }

        is Resource.Empty -> {
            InitialView(onFindApiClick = onFindApiClick)
        }
    }
}

@Composable
fun ErrorMessage(string: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = string)
    }
}

@Composable
fun ShowProgress() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = 35.dp),
            color = Color.Red
        )
        Spacer(modifier = Modifier.width(width = 8.dp))
        Text(text = stringResource(id = R.string.loading))
    }
}

@Composable
fun ShowMyIPInfoView(
    ip: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = RoundedCornerShape(topStart = 15.dp, bottomEnd = 15.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.ip_add),
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = ip,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )
            }

        }

    }

}

@Composable
fun InitialView(modifier: Modifier = Modifier, onFindApiClick: () -> Unit) {
    Column(
        modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = onFindApiClick,
            elevation = ButtonDefaults.buttonElevation(pressedElevation = 3.dp)
        ) {
            Text(
                text = stringResource(id = R.string.btn_label),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
            )
        }
    }
}
