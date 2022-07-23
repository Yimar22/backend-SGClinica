package net.andreanunez.encuestabackend.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.andreanunez.encuestabackend.entities.UserEntity;
import net.andreanunez.encuestabackend.models.requests.UserRegisterRequestModel;

public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String email);

    /**
     * Crear el usuario
     */
    public UserEntity createUser(UserRegisterRequestModel user);

    public UserEntity getUser(String email);
}
