package com.williamheng.graphql

import com.coxautodev.graphql.tools.SchemaParser
import com.williamheng.graphql.dto.Account
import com.williamheng.graphql.dto.Member
import com.williamheng.graphql.dto.Movie
import com.williamheng.graphql.dto.Payload
import com.williamheng.graphql.resolver.AccountQueryResolver
import com.williamheng.graphql.resolver.BookMutationResolver
import com.williamheng.graphql.resolver.BookQueryResolver
import com.williamheng.graphql.resolver.BookResolver
import com.williamheng.graphql.resolver.MemberResolver
import com.williamheng.graphql.resolver.MovieResolver
import com.williamheng.graphql.resolver.QueryResolver
import graphql.ExecutionInput
import graphql.GraphQL
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticResources
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import java.util.*


object GraphQLApplication {

    private val log = LoggerFactory.getLogger(this.javaClass)
    private val data: Collection<Map<Member, Collection<Account>>> = (1..5).map { randomMember() }.toList()
    private val memberData = data.flatMap { it.keys }
    private val accountData = data.flatMap { it.values }.flatMap { it }
    val schema = SchemaParser.newParser()
        .file("schema.graphql")
        .dictionary(Movie::class.java)
        .resolvers(
            QueryResolver(memberData),
            AccountQueryResolver(accountData),
            MemberResolver(accountData),
            BookQueryResolver(),
            BookResolver(),
            BookMutationResolver(),
            MovieResolver()
        )
        .build()
        .makeExecutableSchema()
    val graphQL = GraphQL.newGraphQL(schema).build()

    @JvmStatic
    fun main(args: Array<String>) {
        val server = embeddedServer(Netty, commandLineEnvironment(args))
        server.start()
    }

    fun Application.routes() {
        install(CallLogging) {
            level = Level.INFO
        }
        install(ContentNegotiation) {
            jackson {

            }
        }

        routing {
            staticResources("graphiql", "static/graphiql/index.html")

            post("graphql") {

                val payload = call.receive<Payload>()

                val input = ExecutionInput
                    .newExecutionInput(payload.query)
                    .variables(payload.variables)
                    .operationName(payload.operationName)
                    .build()

                val startTime = System.currentTimeMillis()
                val result = graphQL.execute(input)
                val duration = System.currentTimeMillis() - startTime
                println("Query took $duration ms")

                if (result.errors.isNotEmpty()) {
                    val errorMessages = result.errors.map { it.message }.toList()
                    log.error(errorMessages.toString())
                    call.respond(HttpStatusCode.InternalServerError)
                } else {
                    val data: Any = result.getData()

                    data class GraphQLResponse(val data: Any)
                    call.respond(HttpStatusCode.OK, GraphQLResponse(data))
                }
            }
        }
    }

    private fun randomMember(): Map<Member, Collection<Account>> {
        val accounts = if (Random().nextInt() % 2 == 0) {
            listOf(randomAccount())
        } else {
            listOf(randomAccount(), randomAccount())
        }

        return mapOf(
            Pair(
                Member(
                    id = UUID.randomUUID().toString(),
                    name = UUID.randomUUID().toString(),
                    accountIds = accounts.map { it.id }
                ),
                accounts
            )
        )
    }

    private fun randomAccount(): Account {
        return Account(
            id = UUID.randomUUID().toString(),
            balance = Random().nextInt()
        )
    }

}

