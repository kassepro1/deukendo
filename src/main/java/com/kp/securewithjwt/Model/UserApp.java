package com.kp.securewithjwt.Model;

import java.io.Serializable;
import java.util.List;

public class UserApp implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String email;
    private String tel;
    private String adresse;
    private List<RoleApp> roles;

    public UserApp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<RoleApp> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleApp> roles) {
        this.roles = roles;
    }
}
