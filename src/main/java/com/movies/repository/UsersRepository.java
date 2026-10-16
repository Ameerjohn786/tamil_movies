package com.movies.repository;

import com.movies.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

}
