package com.williamheng.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.williamheng.graphql.dto.Member

class QueryResolver(val data: Collection<Member>): GraphQLQueryResolver {

    fun members(): Collection<Member> {
        return data
    }

}
