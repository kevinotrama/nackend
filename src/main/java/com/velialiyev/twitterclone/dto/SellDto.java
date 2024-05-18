package com.velialiyev.twitterclone.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellDto {
    private Long id;
    private String price;
    private String sellPath;
    private String username;
    private String picturePath;
    private String title;
    private String content;
    private String date;
    private String likes;
}
