package com.williamheng.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLResolver
import com.williamheng.graphql.dto.Account
import com.williamheng.graphql.dto.Member

class MemberResolver(val data: Collection<Account>): GraphQLResolver<Member> {

    fun accounts(member: Member): Collection<Account> {
        return data.filter { member.accountIds.contains(it.id) }
    }

}