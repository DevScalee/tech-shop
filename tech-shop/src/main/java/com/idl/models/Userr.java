package com.idl.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
@Table(name="userrs")
public class Userr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank @Size(max = 25) String firstname ;
    @NotBlank @Size(max = 25) String lastName;
    @Email String email;
    @NotBlank @Size(max = 120) String password;
    String confirmpassword;
    @Enumerated(EnumType.STRING) Role role;
    String Card_Type;
    String Code;

    public Userr(Long id, String firstname, String lastName, String email, String password, String confirmpassword, Role role, String card_Type, String code) {
        this.id = id;
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.role = role;
        Card_Type = card_Type;
        Code = code;
    }

    public Long getId() {
        return id;
    }

    public Userr(Long id) {
        this.id = id;
    }

    public Userr() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCard_Type() {
        return Card_Type;
    }

    public void setCard_Type(String card_Type) {
        Card_Type = card_Type;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
