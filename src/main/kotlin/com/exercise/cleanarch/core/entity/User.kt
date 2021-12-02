package com.exercise.cleanarch.core.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User {

    @Id
    var id: Long? = null

    var name: String? = null

}
