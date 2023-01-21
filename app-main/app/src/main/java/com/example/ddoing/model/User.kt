package com.example.ddoing.model

data class User(
    var uid: String? = "",
    var name: String? = "",
    var friendsList: MutableList<String> = ArrayList(),
    var todoAlertKey: MutableList<String> = ArrayList()
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "name" to name,
            "friendsList" to friendsList,
            "todoAlertKey" to todoAlertKey
        )
    }
}
