package com.kp.securewithjwt.Model;

public class RoleApp {
    private Long id;
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public RoleApp(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public RoleApp() {
    }
}
