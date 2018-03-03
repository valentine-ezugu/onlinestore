package com.valentine.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    /**
     *
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     *
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /**
     *
     * @return
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     *
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
