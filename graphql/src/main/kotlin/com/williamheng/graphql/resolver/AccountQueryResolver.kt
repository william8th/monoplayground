package com.williamheng.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.williamheng.graphql.dto.Account

class AccountQueryResolver(val data: Collection<Account>): GraphQLQueryResolver {

    fun accounts(): Collection<Account> {
        return data
    }

}