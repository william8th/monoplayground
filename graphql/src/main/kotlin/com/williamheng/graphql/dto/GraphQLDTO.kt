package com.williamheng.graphql.dto

data class Member(val id: String, val name: String, val accountIds: Collection<String>)

data class Account(val id: String, val balance: Int)

data class Payload(
    val query: String,
    val operationName: String?,
    val variables: Map<String, Any>? = mapOf()
)
