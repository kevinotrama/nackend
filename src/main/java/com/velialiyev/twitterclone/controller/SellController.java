package com.velialiyev.twitterclone.controller;


import com.velialiyev.twitterclone.dto.*;
import com.velialiyev.twitterclone.entity.SellEntity;
import com.velialiyev.twitterclone.repository.SellRepository;
import com.velialiyev.twitterclone.repository.LikeSellRepositry;
import com.velialiyev.twitterclone.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sell")
@RequiredArgsConstructor
public class SellController {

    private final SellService sellService;
    private final LikeSellRepositry likeSellRepositry;
    String PictureDirectory = "/sellDirectory";

    @GetMapping
    public ResponseEntity<List<SellDto>> getAll(){
        List<SellDto> sells = this.sellService.getAll();
        Collections.reverse(sells);
        return ResponseEntity.ok(sells);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createSell(@RequestBody SellDto sellDto){
        return ResponseEntity.ok(this.sellService.sell(sellDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSell(@PathVariable(name = "id") Long id){
        this.sellService.deleteSell(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/like")
    public ResponseEntity<HttpStatus> like(@RequestBody LikeRetweetBookmarkDto likeRetweetBookmarkDto){
        this.sellService.like(likeRetweetBookmarkDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/like-counter/{id}")
    public ResponseEntity<Integer> likeCounter(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.sellService.likeCounter(id));
    }

    @GetMapping("/picture/get/{id}")
    public ResponseEntity<?> getProfilePicture(@PathVariable(name = "id") Long id){

        Path path = this.sellService.fetchPicturePath(id, this.PictureDirectory);
        ByteArrayResource resource = this.sellService.getPicture(id, this.PictureDirectory);

        return ResponseEntity.ok().contentLength(path.toFile().length()).contentType(MediaType.IMAGE_JPEG).body(resource);
    }


    @PostMapping("/sell-picture/save")
    public ResponseEntity<HttpStatus> saveProfilePicture(
            @RequestParam(name = "sellPicture") MultipartFile sellPicture,
            @RequestParam(name = "id") Long id){

        this.sellService.savePicture(sellPicture, id, this.PictureDirectory);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update")
    public void updateEvent(@RequestBody SellDto event){
        this.sellService.editSell(event);
    }
}
