package com.example.rickandmorty

class DataModel {
    data class Character (
        var info: Info? = Info(),
        var results: ArrayList<CharacterResults> = arrayListOf()
    )

    data class Episode (
        var info: Info? = Info(),
        var results: ArrayList<EpisodeResults> = arrayListOf()
    )

    data class Info (
        var count : Int? = null,
        var pages : Int? = null,
        var next  : String? = null,
        var prev  : String? = null
    )

    data class CharacterResults(
        var id: Int? = null,
        var name: String? = null,
        var status: String? = null,
        var species: String? = null,
        var type: String? = null,
        var gender: String? = null,
        var origin: Origin? = Origin(),
        var location: Location? = Location(),
        var image: String? = null,
        var episode: ArrayList<String> = arrayListOf(),
        var url: String? = null,
        var created: String? = null,
    )

    data class Origin (
        var name: String? = null,
        var url: String? = null,
    )

    data class Location (
        var name: String? = null,
        var url: String? = null,
    )

    data class EpisodeResults (
        var id: Int? = null,
        var name: String? = null,
        var air_date: String? = null,
        var episode: String? = null,
        var characters: ArrayList<String> = arrayListOf(),
        var url: String? = null,
        var created: String? = null
    )
}
