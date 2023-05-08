package com.partyrole.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.partyrole.entities.PartyRole;

@Repository
public interface PartyRoleRepository extends JpaRepository<PartyRole, String>{

	@Query("select p from PARTYROLE p where p.isActive=true")
	List<PartyRole> findAll();
	
	@Query("select p from PARTYROLE p where p.isActive=true and p.id=:Id")
	Optional<PartyRole> findById(@Param("Id") String id);
}
