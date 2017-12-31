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

    public static final class Builder {
        private Course course;

        private Builder() {
            course = new Course();
        }

        public static Builder newCourse() {
            return new Builder();
        }

        public Builder nome(String nome) {
            course.setNome(nome);
            return this;
        }

        public Builder id(Long id) {
            course.setId(id);
            return this;
        }

        public Builder professor(Professor professor) {
            course.setProfessor(professor);
            return this;
        }

        public Course build() {
            return course;
        }
    }
}
