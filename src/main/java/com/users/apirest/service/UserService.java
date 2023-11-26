package com.users.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.users.apirest.model.User;
import com.users.apirest.respository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User saveUser(User usuario) {
        Example<User> example = Example.of(usuario);
        Optional<User> existingUser = userRepository.findOne(example);

        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya existe: " + usuario.getEmail());            
        }
        return userRepository.save(usuario);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User updateUser(String id, User newUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(newUser.getName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setAddress(newUser.getAddress());

            return userRepository.save(user);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id);

    }
}