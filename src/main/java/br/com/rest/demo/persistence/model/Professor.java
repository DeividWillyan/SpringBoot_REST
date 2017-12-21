package br.com.rest.demo.persistence.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Professor extends AbstractEntity {

    @NotEmpty(message = "Nome não pode ser em branco")
    private String name;

    @Email
    @NotEmpty(message = "Email não pode ser em branco")
    @Column(unique = true)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
