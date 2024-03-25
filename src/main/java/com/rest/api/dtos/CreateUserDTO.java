package com.rest.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rest.api.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserDTO {

    @NotNull(message = "First Name is required to complete request")
    private String firstName;

    @NotNull(message = "Last Name is required to complete request")
    private String lastName;

    @Email
    @NotNull(message = "Email is required to complete request")
    private String email;

    @NotNull(message = "password is required to complete request")
    private String password;

    @NotNull(message = "role is required to complete request")
    private Set<Role> role;

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

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
