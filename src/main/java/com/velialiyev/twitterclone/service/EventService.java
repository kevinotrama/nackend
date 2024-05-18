package com.velialiyev.twitterclone.service;

import com.velialiyev.twitterclone.dto.EventDto;
import com.velialiyev.twitterclone.dto.LikeRetweetBookmarkDto;
import com.velialiyev.twitterclone.dto.SellDto;
import com.velialiyev.twitterclone.dto.UserDto;
import com.velialiyev.twitterclone.entity.*;
import com.velialiyev.twitterclone.repository.EventRepository;
import com.velialiyev.twitterclone.repository.LikeEventRepository;
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
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final LikeEventRepository likeEventRepository;
    private final EventRepository eventRepository;
    private final AuthenticationService authenticationService;

    @Transactional
    public Long event(EventDto event) {
        UserEntity user = this.authenticationService.getUserFromJwt();

        EventEntity eventt = this.eventRepository.save(
                EventEntity.builder()
                        .user(user)
                        .title(event.getTitle())
                        .content(event.getContent())
                        .date(Instant.now().toString())
                        .picturePath(event.getPicturePath())
                        .price(event.getPrice())
                        .eventPath(event.getEventPath())
                        .likeCounter(0)
                        .build()
        );

        return eventt.getId();
    }

    @Transactional
    public void like(LikeRetweetBookmarkDto likeRetweetBookmarkDto) {
        UserEntity user = this.authenticationService.getUserFromJwt();
        EventEntity event = this.eventRepository.findById(likeRetweetBookmarkDto.getTweetId()).orElseThrow();
        Optional<LikeEventEntity> optional = this.likeEventRepository.findByUserAndEvent(user, event);

        if(optional.isPresent()){
            event.setLikeCounter(event.getLikeCounter() - 1);
            this.eventRepository.save(event);
            this.likeEventRepository.delete(optional.get());
        }
        else{
            event.setLikeCounter(event.getLikeCounter() + 1);
            this.eventRepository.save(event);
            this.likeEventRepository.save(
                    LikeEventEntity.builder()
                            .user(user)
                            .event(event)
                            .build()
            );
        }
    }

    @Transactional
    public void deleteEvent(Long id){
        EventEntity event = this.eventRepository.findById(id).orElseThrow();

        if (likeEventRepository.findAllByEvent_Id(event.getId()).isPresent())
            likeEventRepository.deleteAll(likeEventRepository.findAllByEvent_Id(event.getId()).get());

        this.eventRepository.delete(event);
    }

    public Integer likeCounter(Long id) {
        return this.eventRepository.findById(id).orElseThrow().getLikeCounter();
    }

    public Path fetchPicturePath(Long id, String directory){
        EventEntity event = this.eventRepository.findById(id).orElseThrow();
        Path picturePath = null;
        if(directory.equals("/eventDirectory"))
            picturePath = Paths.get(event.getPicturePath());

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
            EventEntity event = this.eventRepository.findById(id).orElseThrow();

            if(pictureDirectory.equals("/eventDirectory"))
                event.setPicturePath(picturePath.toAbsolutePath().toString());

            this.eventRepository.save(event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EventDto mapEventToDto(EventEntity entity) {

        Instant now = Instant.now();

        EventDto eventDto = EventDto.builder()
                .id(entity.getId())
                .user(entity.getUser().getUsername())
                .title(entity.getTitle())
                .eventPath(entity.getEventPath())
                .price(entity.getPrice())
                .date(String.valueOf(Duration.between(Instant.parse(entity.getDate()), now).toDays()))
                .picturePath(entity.getPicturePath())
                .content(entity.getContent())
                .likes(String.valueOf(likeEventRepository.findAllByEvent_Id(entity.getId()).get().size()))
                .build();

        return eventDto;
    }

    @Transactional(readOnly = true)
    public List<EventDto> getAll() {
        List<EventEntity> events = this.eventRepository.findAll();
        return events.stream().map(this::mapEventToDto).collect(Collectors.toList());
    }

    public void editEvent(EventDto event) {
        EventEntity eventEntity = this.eventRepository.findById(event.getId()).orElseThrow();
        eventEntity.setContent(event.getContent());
        eventEntity.setPrice(event.getPrice());
        eventEntity.setEventPath(event.getEventPath());
        eventEntity.setTitle(event.getTitle());
        this.eventRepository.save(eventEntity);
    }
}
