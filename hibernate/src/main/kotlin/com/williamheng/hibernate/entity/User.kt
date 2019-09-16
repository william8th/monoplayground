package com.williamheng.hibernate.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity
class User(val name: String): AbstractJpaPersistable<Long>()