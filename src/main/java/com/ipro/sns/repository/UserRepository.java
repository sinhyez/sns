package com.ipro.sns.repository;


import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUsername(String username);

    Optional<UserModel> findByUsernick(String usernick);

}
