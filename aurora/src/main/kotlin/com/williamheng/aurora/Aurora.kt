package com.williamheng.aurora

import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface AuroraDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS users(id BIGSERIAL PRIMARY KEY, name VARCHAR)")
    fun createTable();

    @SqlUpdate("INSERT INTO users(name) VALUES(:name)")
    fun insertUser(@Bind("name") name: String)

}
