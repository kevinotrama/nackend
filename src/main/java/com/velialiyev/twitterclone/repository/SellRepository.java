package com.velialiyev.twitterclone.repository;

import com.velialiyev.twitterclone.entity.SellEntity;
import com.velialiyev.twitterclone.entity.TweetEntity;
import com.velialiyev.twitterclone.entity.TweetType;
import com.velialiyev.twitterclone.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellRepository extends JpaRepository<SellEntity, Long> {
    Optional<SellEntity> findByUser(UserEntity user);
    Optional<SellEntity> findByUserAndId(UserEntity user,Long id);

}
