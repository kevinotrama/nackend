package com.velialiyev.twitterclone.repository;

import com.velialiyev.twitterclone.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeEventRepository extends JpaRepository<LikeEventEntity, Long> {
    Optional<LikeEventEntity> findByUserAndEvent(UserEntity user, EventEntity event);
    Optional<List<LikeEventEntity>> findAllByUser(UserEntity user);
    Optional<List<LikeEventEntity>> findAllByEvent_Id(Long eventid);
}