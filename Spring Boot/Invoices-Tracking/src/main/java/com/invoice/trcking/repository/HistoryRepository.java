package com.invoice.trcking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.trcking.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>{

}
