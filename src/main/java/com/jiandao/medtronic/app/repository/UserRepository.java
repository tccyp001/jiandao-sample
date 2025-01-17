package com.jiandao.medtronic.app.repository;

import com.jiandao.medtronic.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findById(Long id);

  Optional<User> findByEmail(String email);

}
