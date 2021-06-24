package com.github.jamesdeperio.moviedb.network

import com.github.jamesdeperio.moviedb.Platform
import com.github.jamesdeperio.moviedb.network.repository.RestRepository
import com.github.jamesdeperio.moviedb.network.service.RestService
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

class NetworkManager {
    companion object {
        const val API_KEY = "b0fbe003fb2b4f037917ba2e46d27827"
        const val BASE_URL = "https://api.themoviedb.org"
    }
    //region API SERVICE
    val restService : RestService by lazy { RestService(restRepository = RestRepository(networkManager=this)) }
    //endregion

    //region CONFIGURATION
    private val platform = Platform()

    val httpClient = HttpClient {
        expectSuccess = false
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            socketTimeoutMillis = 30000
            connectTimeoutMillis = 30000
        }

        install(UserAgent) {
            agent = "MovieDB - ${platform.platform}"
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP status: ${response.status.value}")
            }
        }


        defaultRequest {
            headers {
                append("Accept", "application/json")
            }
        }
    }

    fun setupSecurityHeader(headersBuilder: HeadersBuilder) {
        //headersBuilder.append("Authorization", "token")
    }

    fun closeHttpClient() {
        httpClient.close()
    }
    //endregion
    
}