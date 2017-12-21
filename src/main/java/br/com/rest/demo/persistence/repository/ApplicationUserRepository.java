package br.com.rest.demo.persistence.repository;

import br.com.rest.demo.persistence.model.ApplicationUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationUserRepository extends PagingAndSortingRepository< ApplicationUser, Long > {

    ApplicationUser findByUsername(String username);

}
