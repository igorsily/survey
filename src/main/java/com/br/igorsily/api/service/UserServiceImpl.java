package com.br.igorsily.api.service;

import com.br.igorsily.api.model.User;
import com.br.igorsily.api.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findUserById(UUID id) throws ObjectNotFoundException {

        var userOptional = this.userRepository.findById(id);

        if (userOptional.isEmpty()) throw new ObjectNotFoundException("Usuário não encontrado", "User");

        return userOptional.get();
    }

    @Override
    public User findUserByEmail(String email) throws ObjectNotFoundException {

        var userOptional = this.userRepository.findByEmail(email);

        if (userOptional.isEmpty()) throw new ObjectNotFoundException("Usuário não encontrado", "User");

        return userOptional.get();
    }


}
