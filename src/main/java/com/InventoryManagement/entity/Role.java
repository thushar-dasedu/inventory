package com.InventoryManagement.entity;


import javax.persistence.*;

@Entity

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

//    public String getRoleDiscription() {
//        return roleDiscription;
//    }
//
//    public void setRoleDiscription(String roleDiscription) {
//        this.roleDiscription = roleDiscription;
//    }
//
//    @Column(name = "role_discription")
//    private String roleDiscription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }
    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id) {
        this.id = id;
    }


//    public Role(int id, String name, String roleDiscription) {
//        this.id = id;
//        this.name = name;
//        this.roleDiscription = roleDiscription;
//    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
