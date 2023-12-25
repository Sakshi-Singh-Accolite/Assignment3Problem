package com.example.Assignment3Problem.repository;

import com.example.Assignment3Problem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String > {
//    @Query(value = """
//            select t from User t where t.userId=:userId""")
//    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
    User findByUserSecret(String userSecret);
//
    User findByUserId(String userId);
}
