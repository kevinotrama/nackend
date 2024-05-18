package com.velialiyev.twitterclone.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String firstName;

    @NotNull
    @NotBlank
    @NotEmpty
    private String lastName;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(unique = true)
    private String username;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    private String bannerPicturePath;
    private String profilePicturePath;
    private String bio;
    private String rol;
    private String location;
    private String personalWebsite;
    private String birthDate;
    private String contentPreference1;
    private String contentPreference2;
    private String contentPreference3;
    private String contentPreference4;
    private String contentPreference5;
    private String timeSpent;
    private String educationalModulesInterest;
    private String educationalModulesTopics;
}
