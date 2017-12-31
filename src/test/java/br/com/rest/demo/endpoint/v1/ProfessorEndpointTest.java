package br.com.rest.demo.endpoint.v1;

import br.com.rest.demo.persistence.model.Professor;

import static org.junit.Assert.*;

public class ProfessorEndpointTest {

    public static Professor mockProfessor() {
        return Professor.Builder.newProfessor()
                .id(1L)
                .name("Joao")
                .email("Joao@Professor.com")
                .build();
    }

}