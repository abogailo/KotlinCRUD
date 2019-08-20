package com.codefest.fessagingcenter.controller

import com.codefest.fessagingcenter.model.Bill
import com.codefest.fessagingcenter.repository.BillRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class BillController(private val billRepository: BillRepository) {

    @GetMapping("/bills")
    fun getAllBills(): List<Bill> =
            billRepository.findAll()


    @PostMapping("/bills")
    fun createNewBill(@Valid @RequestBody bill: Bill): Bill =
            billRepository.save(bill)


    @GetMapping("/bills/{id}")
    fun getBillById(@PathVariable(value = "id") billId: Long): ResponseEntity<Bill> {
        return billRepository.findById(billId).map { bill ->
            ResponseEntity.ok(bill)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/bills/{id}")
    fun updateBillById(@PathVariable(value = "id") billId: Long,
                          @Valid @RequestBody newBill: Bill): ResponseEntity<Bill> {

        return billRepository.findById(billId).map { existingBill ->
            val updatedBill: Bill = existingBill
                    .copy(amount = newBill.amount, balance = newBill.balance, paid = newBill.paid, title = newBill.title)
            ResponseEntity.ok().body(billRepository.save(updatedBill))
        }.orElse(ResponseEntity.notFound().build())

    }

    @PatchMapping("/bills/{id}")
    fun payBillById(@PathVariable(value = "id") billId: Long,
                       @Valid @RequestBody newBill: Bill): ResponseEntity<Bill> {

        return billRepository.findById(billId).map { existingBill ->
            val updatedBill: Bill = existingBill
                    .copy(balance = 0.00, paid = true)
            ResponseEntity.ok().body(billRepository.save(updatedBill))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/bills/{id}")
    fun deleteBillById(@PathVariable(value = "id") billId: Long): ResponseEntity<Void> {

        return billRepository.findById(billId).map { bill  ->
            billRepository.delete(bill)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }


}