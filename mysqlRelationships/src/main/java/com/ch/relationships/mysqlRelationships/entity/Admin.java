package com.ch.relationships.mysqlRelationships.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Admin {
	
	@Id
	private int id;
	
	private String name;

}
