package com.example.Assignment3Problem.repository;

import org.antlr.v4.runtime.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,String> {

//    @Query(value = """
//            select t from Token t inner join UserEntity u\s on t.user.userid""")
    List<Token> findAllValidTokenByUserId(String userId);
    Optional<Token> findByToken(String token);
}
