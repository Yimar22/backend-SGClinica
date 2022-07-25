package net.andreanunez.encuestabackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.andreanunez.encuestabackend.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public UserEntity findByEmail(String email);

    public UserEntity findById(long id);
}
