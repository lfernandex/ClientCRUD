package com.fernandes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandes.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
