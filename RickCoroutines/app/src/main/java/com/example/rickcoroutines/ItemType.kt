package com.example.rickcoroutines

sealed class ItemType {

    data class ButtonObject(val text: String): ItemType()

    data class CharacterCard (
        var id: Int? = null,
        var name: String? = null,
        var status: String? = null,
        var species: String? = null,
        var type: String? = null,
        var gender: String? = null,
        var origin: DataModel.Origin? = DataModel.Origin(),
        var location: DataModel.Location? = DataModel.Location(),
        var image: String? = null,
        var episode: ArrayList<String> = arrayListOf(),
        var url: String? = null,
        var created: String? = null
    ) : ItemType()
}
