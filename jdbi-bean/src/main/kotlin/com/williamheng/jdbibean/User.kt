package com.williamheng.jdbibean

import org.jdbi.v3.core.mapper.Nested
import org.jdbi.v3.core.mapper.reflect.ColumnName
import java.math.BigDecimal

class User {
    var name: String = ""

    var balances: Balances? = null
        @Nested set

    constructor()

    constructor(name: String, balances: Balances) {
        this.name = name
        this.balances = balances
    }

    constructor(name: String, balance1: Int, balance2: BigDecimal) {
        this.name = name
        this.balances = Balances(balance1, balance2)
    }

    override fun toString(): String {
        return "User(name='$name', balances=$balances)"
    }
}

class Balances {
    var balance1: Int = 0

    var balance2: BigDecimal = BigDecimal.ONE
        @ColumnName("balance3") set


    constructor()
    constructor(balance1: Int, balance2: BigDecimal) {
        this.balance1 = balance1
        this.balance2 = balance2
    }

    override fun toString(): String {
        return "Balances(balance1=$balance1, balance2=$balance2)"
    }
}