package com.ibm.TreinamentoBTP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.TreinamentoBTP.model.Correntista;

@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
	List<Correntista> findAllByNomeContains(String nome);
}
