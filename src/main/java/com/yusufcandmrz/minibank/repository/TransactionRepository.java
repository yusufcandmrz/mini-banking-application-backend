package com.yusufcandmrz.minibank.repository;

import com.yusufcandmrz.minibank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.from.id = :accountId OR t.to.id = :accountId")
    List<Transaction> findByAccountId(@Param("accountId") UUID accountId);
}
