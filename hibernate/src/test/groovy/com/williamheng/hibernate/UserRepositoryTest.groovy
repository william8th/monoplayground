package com.williamheng.hibernate

import com.williamheng.hibernate.entity.User
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.junit.Test
import org.zapodot.junit.db.CompatibilityMode
import org.zapodot.junit.db.EmbeddedDatabaseRule

class UserRepositoryTest {

    def database = EmbeddedDatabaseRule.builder()
            .withName("testdb")
            .withMode(CompatibilityMode.PostgreSQL)
            .build()

    @Lazy
    SessionFactory sessionFactory = {

        def properties = new Properties()
        properties["hibernate.connection.url"] = database.connectionJdbcUrl
        properties["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQLDialect"
        properties["hibernate.connection.username"] = "sa"
        properties["hibernate.connection.password"] = ""
        properties["hibernate.connection.driver_class"] = "org.h2.Driver"
        properties["show_sql"] = true

        println(properties)

        new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory()
    }()

    @Test
    void testInsert() {
        def repository = new UserRepository(sessionFactory)
        def user = new User("william")
        repository.insertUser(user)

        def storedUser = repository.findUserByName("william")
        assert storedUser == user
    }

}
