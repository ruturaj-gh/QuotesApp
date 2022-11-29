package com.example.QuotesApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.QuotesApp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(applicationContext)).get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())
        binding.PREVIOUS.setOnClickListener {

            setQuote(mainViewModel.previouseQuote())

        }

        binding.NEXT.setOnClickListener{
            setQuote(mainViewModel.nextQuote())

        }
        binding.floatingActionButton.setOnClickListener {
            val intent= Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
            startActivity(intent)
        }










    }

    private fun setQuote(Quote:QuoteList)
    {
        binding.quoteText.text=Quote.text
        binding.quoteAuthor.text=Quote.author

    }
}