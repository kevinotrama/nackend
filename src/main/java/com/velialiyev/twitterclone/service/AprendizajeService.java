package com.velialiyev.twitterclone.service;

import com.velialiyev.twitterclone.dto.AprendizajeDto;
import com.velialiyev.twitterclone.dto.EventDto;
import com.velialiyev.twitterclone.entity.Aprendizaje;
import com.velialiyev.twitterclone.entity.EventEntity;
import com.velialiyev.twitterclone.entity.UserEntity;
import com.velialiyev.twitterclone.repository.AprendizajeRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AprendizajeService {
    private final AprendizajeRepository aprendizajeRepository;

    @Transactional
    public Long New(AprendizajeDto aprendizajeDto) {

        Aprendizaje eventt = this.aprendizajeRepository.save(
                Aprendizaje.builder()
                        .titulo(aprendizajeDto.getTitulo())
                        .Contenido(aprendizajeDto.getContenido())
                        .Actividades(aprendizajeDto.getActividades())
                        .Objetivos(aprendizajeDto.getObjetivos())
                        .linkyoutube(aprendizajeDto.getLinkyoutube())
                        .introduccion(aprendizajeDto.getIntroduccion())
                        .picturePath(aprendizajeDto.getPicturePath())
                        .build());

        return eventt.getId();
    }
    private AprendizajeDto mapAprendizajeToDto(Aprendizaje aprendizaje) {

        AprendizajeDto aprendizajeDto = AprendizajeDto.builder()
                .id(aprendizaje.getId().toString())
                .titulo(aprendizaje.getTitulo())
                .contenido(aprendizaje.getContenido())
                .actividades(aprendizaje.getActividades())
                .objetivos(aprendizaje.getObjetivos())
                .linkyoutube(aprendizaje.getLinkyoutube())
                .introduccion(aprendizaje.getIntroduccion())
                .picturePath(aprendizaje.getPicturePath())
                .build();

        return aprendizajeDto;
    }

    public Path fetchPicturePath(Long id, String directory){
        Aprendizaje event = this.aprendizajeRepository.findById(id).orElseThrow();
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
    @Transactional(readOnly = true)
    public List<AprendizajeDto> getAll() {
        List<Aprendizaje> events = this.aprendizajeRepository.findAll();
        return events.stream().map(this::mapAprendizajeToDto).collect(Collectors.toList());
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
            Aprendizaje event = this.aprendizajeRepository.findById(id).orElseThrow();

            if(pictureDirectory.equals("/eventDirectory"))
                event.setPicturePath(picturePath.toAbsolutePath().toString());

            this.aprendizajeRepository.save(event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
