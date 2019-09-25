package com.williamheng.jdbibean

import org.jdbi.v3.testing.JdbiRule
import org.junit.Rule
import org.junit.Test

class JdbiBean {

    @Rule
    public JdbiRule postgres = JdbiRule.embeddedPostgres()

    @Test
    void mapToBeanTest() {
        def jdbi = postgres.getJdbi().installPlugins()
        jdbi.withExtension(UserDao.class, { dao ->
            dao.createTable()
            dao.insertUser(new User("William", 123, 456.0))
        })

        def user = jdbi.withHandle { handle ->
            handle.createQuery("SELECT * FROM users")
            .mapToBean(User.class)
            .findOne()
        }

        assert user.isPresent()
        println(user.get())
        assert user.get().balances != null
    }

}
