package com.velialiyev.twitterclone.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private String firstName;
    private String lastName;
    private String username;
    private String rol;
    private String email;
    private String password;
    private String bannerPicturePath;
    private String profilePicturePath;
    private String bio;
    private String location;
    private String personalWebsite;
    private String birthDate;
    private String contentPreference1;
    private String contentPreference2;
    private String contentPreference3;
    private String contentPreference4;
    private String contentPreference5;
    private String regionPreference;
    private String eventRecommendations;
    private String notificationFrequency;
    private String timeSpent;
    private String explorationPreference;
    private String contentFormat;
    private String contentLength;
    private String ancestralLanguagesImportance;
    private String virtualCoursesInterest;
    private String educationalModulesInterest;
    private String educationalModulesTopics;
    private String knowledgeLevel;
    private String contentAdaptationInterest;
    private String joinMotivation;
    private String participationExpectations;
    private String communityParticipationInterest;
    private String communityTopicsInterest;
    private String contentContributionInterest;
    private String contributionTypes;
}
