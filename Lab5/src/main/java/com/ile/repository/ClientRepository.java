package com.ile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ile.persistence.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}

