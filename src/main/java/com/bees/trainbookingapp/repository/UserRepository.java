package com.bees.trainbookingapp.repository;

import com.bees.trainbookingapp.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
  User findByEmail( String email );
}
