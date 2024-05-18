package com.velialiyev.twitterclone.service;

import com.velialiyev.twitterclone.dto.EventDto;
import com.velialiyev.twitterclone.dto.LikeRetweetBookmarkDto;
import com.velialiyev.twitterclone.dto.SellDto;
import com.velialiyev.twitterclone.entity.*;
import com.velialiyev.twitterclone.repository.LikeSellRepositry;
import com.velialiyev.twitterclone.repository.SellRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellService {

    private final LikeSellRepositry likeSellRepository;
    private final SellRepository sellRepository;
    private final AuthenticationService authenticationService;

    @Transactional
    public Long sell(SellDto sellDto) {
        UserEntity user = this.authenticationService.getUserFromJwt();

        SellEntity sell = this.sellRepository.save(
                SellEntity.builder()
                        .user(user)
                        .title(sellDto.getTitle())
                        .content(sellDto.getContent())
                        .date(sellDto.getDate())
                        .picturePath(sellDto.getPicturePath())
                        .price(sellDto.getPrice())
                        .sellPath(sellDto.getSellPath())
                        .likeCounter(0)
                        .build()
        );

        return sell.getId();
    }

    @Transactional
    public void like(LikeRetweetBookmarkDto likeRetweetBookmarkDto) {
        UserEntity user = this.authenticationService.getUserFromJwt();
        SellEntity sell = this.sellRepository.findById(likeRetweetBookmarkDto.getTweetId()).orElseThrow();
        Optional<LikeSellEntity> optional = this.likeSellRepository.findByUserAndSell(user, sell);

        if(optional.isPresent()){
            sell.setLikeCounter(sell.getLikeCounter() - 1);
            this.sellRepository.save(sell);
            this.likeSellRepository.delete(optional.get());
        }
        else{
            sell.setLikeCounter(sell.getLikeCounter() + 1);
            this.sellRepository.save(sell);
            this.likeSellRepository.save(
                    LikeSellEntity.builder()
                            .user(user)
                            .sell(sell)
                            .build()
            );
        }
    }

    public void editSell(SellDto sell) {
        SellEntity sellEntity = this.sellRepository.findById(sell.getId()).orElseThrow();
        sellEntity.setContent(sell.getContent());
        sellEntity.setPrice(sell.getPrice());
        sellEntity.setSellPath(sell.getSellPath());
        sellEntity.setTitle(sell.getTitle());
        this.sellRepository.save(sellEntity);
    }
    @Transactional
    public void deleteSell(Long id){
        SellEntity sell = this.sellRepository.findById(id).orElseThrow();
        this.sellRepository.deleteById(id);
    }

    public Integer likeCounter(Long id) {
        return this.sellRepository.findById(id).orElseThrow().getLikeCounter();
    }

    public Path fetchPicturePath(Long id, String directory){
        SellEntity sell = this.sellRepository.findById(id).orElseThrow();
        Path picturePath = null;
        if(directory.equals("/sellDirectory"))
            picturePath = Paths.get(sell.getPicturePath());

        return picturePath;
    }

    public ByteArrayResource getPicture(Long id, String directory) {

        Path picturePath = this.fetchPicturePath(id, directory);
        try {
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(picturePath));
            return resource;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional
    public void savePicture(MultipartFile picture, Long id, String pictureDirectory) {

        String userFolder = "./uploads/" + id;
        String pictureName = "/" + id+ "." + FilenameUtils.getExtension(picture.getOriginalFilename());
        File directory = new File(userFolder + pictureDirectory);

        if(!directory.exists()){
            directory.mkdirs();
        }

        try {
            byte[] profilePictureBytes = picture.getBytes();
            Path picturePath = Paths.get(directory.getPath() + pictureName);
            FileUtils.cleanDirectory(directory);
            Files.write(picturePath, profilePictureBytes);
            SellEntity sellEntity = this.sellRepository.findById(id).orElseThrow();

            if(pictureDirectory.equals("/sellDirectory"))
                sellEntity.setPicturePath(picturePath.toAbsolutePath().toString());

            this.sellRepository.save(sellEntity);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SellDto mapSellToDto(SellEntity entity) {

        SellDto sellDto = SellDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .sellPath(entity.getSellPath())
                .price(entity.getPrice())
                .username(entity.getUser().getUsername())
                .date(entity.getDate())
                .picturePath(entity.getPicturePath())
                .content(entity.getContent())
                .likes(String.valueOf(likeSellRepository.findAllBySell_Id(entity.getId()).get().size()))
                .build();

        return sellDto;
    }

    @Transactional(readOnly = true)
    public List<SellDto> getAll() {
        List<SellEntity> sells = this.sellRepository.findAll();
        return sells.stream().map(this::mapSellToDto).collect(Collectors.toList());
    }
}
