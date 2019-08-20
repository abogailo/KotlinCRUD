package com.codefest.fessagingcenter.repository

import com.codefest.fessagingcenter.model.Bill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BillRepository : JpaRepository<Bill, Long>