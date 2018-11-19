package com.ibm.TreinamentoBTP.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.TreinamentoBTP.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	Optional<Conta> findBynumConta(Integer numConta);
}
