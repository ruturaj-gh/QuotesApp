package com.example.QuotesApp


import android.content.Context
import android.os.StrictMode
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.QuotesApp.RetroFitHelper.url
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers.io
import retrofit2.Response
import java.io.File
import java.net.URL
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.FileChannel.open
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import org.xml.sax.Parser
import org.xml.sax.XMLReader
import javax.xml.parsers.SAXParser


class MainViewModel(val context: Context): ViewModel() {

    private var QuoteList:Array<QuoteList> =emptyArray()

     private var index=0


    init {
        QuoteList= loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<QuoteList> {
        // Disable the `NetworkOnMainThreadException` and make sure it is just logged.
        StrictMode
            .setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())





        Thread{
            Thread.sleep(100)
        }
        val response=URL("https://type.fit/api/quotes").readText()
        Thread{
            Thread.sleep(100)
        }






        val inputStream=context.assets.open("Quotes.json")

        val size:Int=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer,Charsets.UTF_8)
        val gson=Gson()


        return gson.fromJson(response,Array<QuoteList>::class.java)

    }

    fun getQuote()=QuoteList[index]
    fun nextQuote()=QuoteList[++index]

    fun previouseQuote()=if(index==0){
        index=index+1
        QuoteList[--index]
    }else
    {
        QuoteList[--index]

    }




}

