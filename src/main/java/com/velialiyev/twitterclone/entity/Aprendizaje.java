package com.velialiyev.twitterclone.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aprendizaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String introduccion;
    private String Objetivos;
    private String Contenido;
    private String Actividades;
    private String linkyoutube;
    private String picturePath;
}
