package com.williamheng.jdbibean

import org.jdbi.v3.sqlobject.customizer.BindBean
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface UserDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS users(id BIGSERIAL PRIMARY KEY, name VARCHAR, balance1 INT, balance2 INT)")
    fun createTable();

    @SqlUpdate("INSERT INTO users(name, balance1, balance2) VALUES(:name, :balances.balance1, :balances.balance2)")
    fun insertUser(@BindBean user: User)

}
