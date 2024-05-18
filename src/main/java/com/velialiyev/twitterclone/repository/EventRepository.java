package com.velialiyev.twitterclone.repository;

import com.velialiyev.twitterclone.entity.EventEntity;
import com.velialiyev.twitterclone.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findByUser(UserEntity user);
    Optional<EventEntity> findByUserAndId(UserEntity user, Long id);

}
