package com.codefest.fessagingcenter.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
data class Bill (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

         //hibernate stuff
        val amount: Double = 0.00,


        val title: String = "",

        @get: NotNull
        val balance: Double = 0.00,

        @get: NotNull
        val paid: Boolean = false
)