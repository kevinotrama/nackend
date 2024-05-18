package com.velialiyev.twitterclone.repository;

import com.velialiyev.twitterclone.entity.LikeSellEntity;
import com.velialiyev.twitterclone.entity.SellEntity;
import com.velialiyev.twitterclone.entity.TweetEntity;
import com.velialiyev.twitterclone.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeSellRepositry extends JpaRepository<LikeSellEntity, Long>{
        Optional<LikeSellEntity> findByUserAndSell(UserEntity user, SellEntity sell);
        Optional<List<LikeSellEntity>> findAllByUser(UserEntity user);

        Optional<List<LikeSellEntity>> findAllBySell_Id(Long sellid);
}
