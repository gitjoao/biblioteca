package com.biblioteca.model

import com.biblioteca.controller.exception.BadRequestException
import com.biblioteca.enums.BookErrors
import com.biblioteca.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.*

@Entity(name= "book")
data class BookModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null,
) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.DELETADO || field == BookStatus.CANCELADO ) {
                throw BadRequestException(BookErrors.BOOK_NOT_UPDATE.code)
            }
            field = value
        }
    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus?): this(id, name, price, customer) {
            this.status = status
        }
}