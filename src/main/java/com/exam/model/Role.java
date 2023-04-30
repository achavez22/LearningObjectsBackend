package com.exam.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    private Long rolId;
    private String rolName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
    private Set<UserRole> userRoles = new HashSet<>();

    public Role(){

    }

    public Role(Long rolId, String rolName) {
        this.rolId = rolId;
        this.rolName = rolName;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolNombre) {
        this.rolName = rolNombre;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> usuarioRoles) {
        this.userRoles = usuarioRoles;
    }
}
