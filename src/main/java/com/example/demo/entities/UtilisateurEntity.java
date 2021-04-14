package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity(name="utilisateur")
@PrimaryKeyJoinColumn(name ="userId" )
public class UtilisateurEntity extends UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6825689550190348766L;
	


}
