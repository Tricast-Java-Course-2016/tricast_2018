package com.tricast.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findById(Long id);

    Account findByUserName(String username);

}