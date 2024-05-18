package com.velialiyev.twitterclone.dto;

import com.velialiyev.twitterclone.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String price;
    private String eventPath;
    private String picturePath;
    private String user;
    private String title;
    private String content;
    private String date;
    private String likes;
}
