package com.waa.lab.repository;

import com.waa.lab.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    List<RefreshToken> findAll();
    Optional<RefreshToken> findByUserId(long userId);
    Optional<RefreshToken> findByToken(String token);
}
