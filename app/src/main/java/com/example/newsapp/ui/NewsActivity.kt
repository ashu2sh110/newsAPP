package com.example.newsapp.ui

import com.example.newsprojectpractice.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.repository.NewsRepository
import com.example.newsprojectpractice.databinding.ActivityNewsBinding
import androidx.lifecycle.ViewModelProvider

class NewsActivity : AppCompatActivity() {
    lateinit var  newsViewModel: NewsViewModel
    lateinit var binding: ActivityNewsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))

        val viewModelProviderFactor = NewsViewModelProviderFactor(application, newsRepository)

        newsViewModel = ViewModelProvider(this, viewModelProviderFactor).get(NewsViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }





}
