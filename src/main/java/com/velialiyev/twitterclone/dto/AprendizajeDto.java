package com.velialiyev.twitterclone.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AprendizajeDto {

    private String id;
    private String titulo;
    private String introduccion;
    private String objetivos;
    private String contenido;
    private String actividades;
    private String linkyoutube;
    private String picturePath;
}
