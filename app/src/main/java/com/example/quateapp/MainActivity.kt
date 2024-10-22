package com.example.quateapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quateapp.Screens.QuoteDetail
import com.example.quateapp.Screens.QuoteList
import com.example.quateapp.Screens.QuoteListItem
import com.example.quateapp.Screens.QuoteListScreen
import com.example.quateapp.ui.theme.QuateAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            DataManager.loadAssetFromFile(applicationContext)//load data from assets, in background thread
        }

        setContent {
            QuateAppTheme {
                App()
                }
            }
        }
    }
@Composable
fun App() {
          if (DataManager.isDataLoaded.value){
              if (DataManager.curPage.value == Pages.LISTING){

                  QuoteListScreen(data = DataManager.data){
                      DataManager.switchPages(it)
                  }
              }else{
                  DataManager.curQuote?.let { QuoteDetail(it) }
              }

          }else{
              Box(
                  contentAlignment = Alignment.Center,
                  modifier = Modifier.fillMaxSize(1f)
              ){
                  Text(
                      text = "Loading...",
                      style = MaterialTheme.typography.bodyLarge
                  )
              }

          }
    
}
enum class Pages{
    LISTING,
    DETAIL
}


