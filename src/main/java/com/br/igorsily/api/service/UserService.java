package com.br.igorsily.api.service;

import com.br.igorsily.api.model.User;
import org.hibernate.ObjectNotFoundException;

import java.util.UUID;

public interface UserService {

    User createUser(User user);

    User findUserById(UUID id) throws ObjectNotFoundException;

    User findUserByEmail(String email) throws ObjectNotFoundException;
}
