package com.example.androidgraphql

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

object Apollo {

    fun setupApolloClient() : ApolloClient {

        val okHttpClient = OkHttpClient.Builder()
            .build()

        return ApolloClient.builder()
            .serverUrl("https://countries.trevorblades.com/")
            .okHttpClient(okHttpClient)
            .build()
    }
}