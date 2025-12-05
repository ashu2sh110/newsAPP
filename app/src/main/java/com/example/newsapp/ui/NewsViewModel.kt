package com.example.newsapp.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.util.Resource
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class NewsViewModel(app: Application, val newsRepository: NewsRepository): AndroidViewModel(app) {
    val headlines: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var headlinesPage = 1
    var headlinesResponse: NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchPage = 1
    var searchNewsResponse: NewsResponse? = null
    var newSearchQuery: String? = null
    var oldSearchQuery: String? = null



    fun getHeadlines(countryCode: String) = viewModelScope.launch {
        headlinesInternet(countryCode)
    }
    fun searchNews(seachQuery: String) = viewModelScope.launch {
        searchForNews(seachQuery)
    }


    private fun handleHeadLineResponse(response: Response<NewsResponse>){
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                headlinesPage++
                if(headlinesResponse == null){
                    headlinesResponse = resultResponse
                }else {
                    val oldArticles = headlinesResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                headlines.postValue(Resource.Success(headlinesResponse ?: resultResponse))
            }
        } else {
            headlines.postValue(Resource.Error(response.message()))
        }
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                if(searchNewsResponse == null || newSearchQuery != oldSearchQuery){
                    searchPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = resultResponse
                }
                else {
                    searchPage++
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                searchNews.postValue(Resource.Success(searchNewsResponse ?: resultResponse))

            }
        } else {
            searchNews.postValue(Resource.Error(response.message()))
        }
    }
    fun addToFavourite(article: Article) = viewModelScope.launch {
        newsRepository.upsert((article))
    }
    fun getFavouriteNews() = newsRepository.getFavourtiteNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
    fun interConnection(context: Context): Boolean{
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply{
            return getNetworkCapabilities(activeNetwork)?. run {
            when{
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
            }?: false


        }

    }
    suspend fun headlinesInternet(countryCode: String){
        headlines.postValue(Resource.Loading())
        try{
            if(interConnection(this.getApplication())){
                val response= newsRepository.getHeadLines(countryCode, headlinesPage)
                handleHeadLineResponse((response))
            }else{
                headlines.postValue(Resource.Error("NO internet"))
            }
        }catch(t: Throwable){
            when(t){
                is IOException -> headlines.postValue(Resource.Error("unable to connect"))
                else -> headlines.postValue(Resource.Error("NO signal"))
            }
        }

    }

    suspend fun searchForNews(searchQuery: String){
        newSearchQuery = searchQuery
        searchNews.postValue(Resource.Loading())
        try{
            if(interConnection(this.getApplication())){
                val response= newsRepository.searchNews(searchQuery, searchPage)
                handleSearchNewsResponse(response)
            }else{
                searchNews.postValue(Resource.Error("NO internet"))
            }
        }catch(t: Throwable){
            when(t){
                is IOException -> searchNews.postValue(Resource.Error("unable to connect"))
                else -> searchNews.postValue(Resource.Error("NO signal"))
            }
        }

    }
}