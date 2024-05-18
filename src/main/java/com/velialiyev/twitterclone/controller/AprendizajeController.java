package com.velialiyev.twitterclone.controller;

import com.velialiyev.twitterclone.dto.AprendizajeDto;
import com.velialiyev.twitterclone.dto.EventDto;
import com.velialiyev.twitterclone.service.AprendizajeService;
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
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/aprendizaje")
@RequiredArgsConstructor
public class AprendizajeController {

    String PictureDirectory = "/eventDirectory";
    private final AprendizajeService aprendizajeService;

    @GetMapping
    public ResponseEntity<List<AprendizajeDto>> getAll(){
        List<AprendizajeDto> event = this.aprendizajeService.getAll();
        return ResponseEntity.ok(event);
    }

    @PostMapping("/event-picture/save")
    public ResponseEntity<HttpStatus> saveProfilePicture(
            @RequestParam(name = "eventPicture") MultipartFile eventPicture,
            @RequestParam(name = "id") Long id){

        this.aprendizajeService.savePicture(eventPicture, id, this.PictureDirectory);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/create")
    public ResponseEntity<Long> createAprendizaje(@RequestBody AprendizajeDto aprendizajeDto){
        return ResponseEntity.ok(this.aprendizajeService.New(aprendizajeDto));
    }

    @GetMapping("/picture/get/{id}")
    public ResponseEntity<?> getProfilePicture(@PathVariable(name = "id") Long id){

        Path path = this.aprendizajeService.fetchPicturePath(id, this.PictureDirectory);
        ByteArrayResource resource = this.aprendizajeService.getPicture(id, this.PictureDirectory);

        return ResponseEntity.ok().contentLength(path.toFile().length()).contentType(MediaType.IMAGE_JPEG).body(resource);
    }
}
