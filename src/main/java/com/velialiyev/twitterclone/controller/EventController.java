package com.velialiyev.twitterclone.controller;

import com.velialiyev.twitterclone.dto.EventDto;
import com.velialiyev.twitterclone.dto.LikeRetweetBookmarkDto;
import com.velialiyev.twitterclone.dto.UserDto;
import com.velialiyev.twitterclone.repository.LikeEventRepository;
import com.velialiyev.twitterclone.service.EventService;
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
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final LikeEventRepository likeEventRepository;
    String PictureDirectory = "/eventDirectory";

    @GetMapping
    public ResponseEntity<List<EventDto>> getAll(){
        List<EventDto> event = this.eventService.getAll();

        event = event.stream()
                .filter(even -> Integer.parseInt(even.getDate()) < 15)
                .collect(Collectors.toList());

        Collections.reverse(event);
        return ResponseEntity.ok(event);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createEvent(@RequestBody EventDto eventDto){
        return ResponseEntity.ok(this.eventService.event(eventDto));
    }

    @PostMapping("/like")
    public ResponseEntity<HttpStatus> like(@RequestBody LikeRetweetBookmarkDto likeRetweetBookmarkDto){
        this.eventService.like(likeRetweetBookmarkDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/like-counter/{id}")
    public ResponseEntity<Integer> likeCounter(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(this.eventService.likeCounter(id));
    }

    @GetMapping("/picture/get/{id}")
    public ResponseEntity<?> getProfilePicture(@PathVariable(name = "id") Long id){

        Path path = this.eventService.fetchPicturePath(id, this.PictureDirectory);
        ByteArrayResource resource = this.eventService.getPicture(id, this.PictureDirectory);

        return ResponseEntity.ok().contentLength(path.toFile().length()).contentType(MediaType.IMAGE_JPEG).body(resource);
    }

    @PostMapping("/event-picture/save")
    public ResponseEntity<HttpStatus> saveProfilePicture(
            @RequestParam(name = "eventPicture") MultipartFile eventPicture,
            @RequestParam(name = "id") Long id){

        this.eventService.savePicture(eventPicture, id, this.PictureDirectory);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public void updateEvent(@RequestBody EventDto event){
        this.eventService.editEvent(event);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable(name = "id") Long id){
        this.eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
