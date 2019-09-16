package com.williamheng.jdbibean

import org.jdbi.v3.core.mapper.Nested

class User {
    var name: String = ""

    var balances: Balances? = null
        @Nested set

    constructor()

    constructor(name: String, balances: Balances) {
        this.name = name
        this.balances = balances
    }

    constructor(name: String, balance1: Int, balance2: Int) {
        this.name = name
        this.balances = Balances(balance1, balance2)
    }

    override fun toString(): String {
        return "User(name='$name', balances=$balances)"
    }
}

class Balances {
    var balance1: Int = 0
    var balance2: Int = 0

    constructor()
    constructor(balance1: Int, balance2: Int) {
        this.balance1 = balance1
        this.balance2 = balance2
    }

    override fun toString(): String {
        return "Balances(balance1=$balance1, balance2=$balance2)"
    }
}