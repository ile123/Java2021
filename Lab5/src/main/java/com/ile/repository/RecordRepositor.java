package com.ile.repository;

import com.ile.persistence.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepositor extends JpaRepository<Record,Long> {
}