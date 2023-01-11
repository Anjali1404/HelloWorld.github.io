package com.ch.relationships.mysqlRelationships.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ch.relationships.mysqlRelationships.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
	Admin findByName(String name);
}
