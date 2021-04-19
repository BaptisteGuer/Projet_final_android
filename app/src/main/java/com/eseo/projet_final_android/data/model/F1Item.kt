package com.eseo.projet_final_android.data.model

import com.google.gson.annotations.SerializedName

//Cette classe correspond au format du JSON récupéré par l'API fournie par https://ergast.com
data class F1Item(
    @SerializedName("MRData") val MRData: MRData
)

data class MRData(
    @SerializedName("xmlns") val xmlns: String,
    @SerializedName("series") val series: String,
    @SerializedName("url") val url: String,
    @SerializedName("limit") val limit: String,
    @SerializedName("offset") val offset: String,
    @SerializedName("total") val total: String,
    @SerializedName("RaceTable") val raceTable: RaceTable
)

data class RaceTable(
    @SerializedName("season") val season: String,
    @SerializedName("Races") val races: List<Races>
)

data class Races(
    @SerializedName("season") val season: String,
    @SerializedName("round") val round: String,
    @SerializedName("url") val url: String,
    @SerializedName("raceName") val raceName: String,
    @SerializedName("Circuit") val circuit: Circuit,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String
)

data class Circuit(
    @SerializedName("circuitId") val circuitId: String,
    @SerializedName("url") val url: String,
    @SerializedName("circuitName") val circuitName: String,
    @SerializedName("Location") val location: Location
)

data class Location(
    @SerializedName("lat") val lat: String,
    @SerializedName("long") val long: String,
    @SerializedName("locality") val locality: String,
    @SerializedName("country") val country: String
)
