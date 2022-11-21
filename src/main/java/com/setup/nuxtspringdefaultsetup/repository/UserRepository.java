package com.setup.nuxtspringdefaultsetup.repository;

import com.setup.nuxtspringdefaultsetup.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findByUserId(Long userId);

}
