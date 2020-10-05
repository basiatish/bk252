package ru.max.bk252_with_coroutines.Data

class UserData {

    var id: Int? = null
    var begin_date: String? = null
    var description: String? = null
    var end_date: String? = null
    var name: String? = null
    var short: String? = null
    var status: String? = null

    constructor(id: Int, begin_date: String, description: String, end_date: String, name: String, short: String, status: String) {
        this.id = id
        this.begin_date = begin_date
        this.description = description
        this.end_date = end_date
        this.name = name
        this.short = short
        this.status = status
    }
}