package com.example.demo.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@NotBlank(message="Ce champ ne doit etre null !")
	@Size(min=3, message="Ce champ ne doit avoir au moins 3 Caractères !")
	private String firstName;
	
	@NotBlank(message="Ce champ ne doit etre null !")
	@Size(min=3, message="Ce champ ne doit avoir au moins 3 Caractères  !")
	private String lastName;
	
	@NotNull(message="Ce champ ne doit etre null !")
	@Email(message = "Ce champ doit respecter le format email ! ")
	private String email;
	
	@NotNull(message="Ce champ ne doit etre null !")
	@Size(min=8, message = "entrer un mot de passe au mois avec 8 carac ! ")
	@Size(max=16, message = "entrer un mot de passe au max 16 carac ! ")
	@Pattern(regexp="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",message="ce password doit avoir des lettres en Maj et Minsc et numbers")
	private String password;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
