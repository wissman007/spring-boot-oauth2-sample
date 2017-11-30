package com.gigy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigy.model.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
	
	List<Party> findAll();

}
