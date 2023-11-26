package com.users.apirest.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.users.apirest.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
