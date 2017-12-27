package br.com.rest.demo.persistence.repository;

import br.com.rest.demo.persistence.model.Professor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {

    Professor findByEmail(String email);

}
