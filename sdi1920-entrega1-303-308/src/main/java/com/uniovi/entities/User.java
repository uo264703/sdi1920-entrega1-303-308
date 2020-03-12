package com.uniovi.entities;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;

	private String password;
	@Transient // propiedad que no se almacena en la tabla.
	private String passwordConfirm;

	@OneToMany(mappedBy = "usuario1", cascade = CascadeType.ALL)
	private Set<Amistad> friends = new HashSet<Amistad>();

	@OneToMany(mappedBy = "usuario2")
	private Set<Amistad> friendOf = new HashSet<Amistad>();

	@OneToMany(mappedBy = "pidePeticion", cascade = CascadeType.ALL)
	private Set<Peticion> peticionesEnviadas = new HashSet<Peticion>();

	@OneToMany(mappedBy = "recibePeticion")
	private Set<Peticion> peticionesRecibidas = new HashSet<Peticion>();

	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Amistad> getFriends() {
		return friends;
	}

	public void setFriends(Set<Amistad> friends) {
		this.friends = friends;
	}

	public Set<Amistad> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(Set<Amistad> friendOf) {
		this.friendOf = friendOf;
	}

	public Set<Peticion> getPeticionesEnviadas() {
		return peticionesEnviadas;
	}

	public void setPeticionesEnviadas(Set<Peticion> peticionesEnviadas) {
		this.peticionesEnviadas = peticionesEnviadas;
	}

	public Set<Peticion> getPeticionesRecibidas() {
		return peticionesRecibidas;
	}

	public void setPeticionesRecibidas(Set<Peticion> peticionesRecibidas) {
		this.peticionesRecibidas = peticionesRecibidas;
	}

	public void addFriend(User userToAdd) {
		Peticion peticion = new Peticion(this, userToAdd);

		if (!this.getPeticionesEnviadas().contains(peticion)) {
			this.getPeticionesEnviadas().add(peticion);
			userToAdd.getPeticionesRecibidas().add(peticion);
		}
	}

}
