package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name="id")
public class AdminEntity extends UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5992760681299287902L;
	
	
	
}
