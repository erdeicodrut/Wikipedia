package com.example.wikipedia.providers

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.example.wikipedia.models.Urls
import com.example.wikipedia.models.WikiResult
import java.io.Reader
import java.lang.Exception
import javax.inject.Inject

class ArticleDataProvider @Inject constructor() {

    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "Pluralsight Wikipedia")
    }


    fun search(term: String,skip:Int, take:Int, responseHandler:(result:WikiResult) -> Unit?){
        Urls.getSearchUrl(term,skip,take).httpGet().responseObject(WikipediaDeserializer()){ _, response, result ->

            if (response.statusCode != 200){
                throw Exception("Unable to get articles")
            }

            val data  = result.component1()!!
            responseHandler.invoke(data)
        }
    }

    fun getRandom(take:Int, responseHandler:(result:WikiResult) -> Unit? ){
        Urls.getRandomUrl(take).httpGet().responseObject(WikipediaDeserializer()){ _, response, result ->

            if (response.statusCode != 200){
                throw Exception("Unable to get articles")
            }
            val data = result.component1()!!
            responseHandler.invoke(data)
        }
    }

    class WikipediaDeserializer: ResponseDeserializable<WikiResult>{

        override fun deserialize(reader: Reader): WikiResult? = Gson().fromJson(reader,WikiResult::class.java)

    }
}