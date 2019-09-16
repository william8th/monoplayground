package com.williamheng.hibernate

import com.williamheng.hibernate.entity.User
import org.hibernate.SessionFactory

class UserRepository(private val sessionFactory: SessionFactory) {

    fun insertUser(user: User) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.persist(user)
            session.transaction.commit()
        }
    }

    fun findUserByName(name: String): User {
        sessionFactory.openSession().use {  session ->
            session.beginTransaction()
            val user = session.find(User::class.java, name)
            session.detach(user)
            return user
        }
    }

}
