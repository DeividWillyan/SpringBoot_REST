package br.com.rest.demo.persistence.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Course extends AbstractEntity{

    @NotEmpty(message = "O Campo não pode estar branco.")
    @ApiModelProperty(notes = "Nome do curso")
    private String nome;

    @ManyToOne(optional = false)
    private Professor professor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
