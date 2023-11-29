package com.project.Glog.dto;

import com.project.Glog.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class PostPreviewDto {
    private String blogUrl;
    private Long postId;
    private Long categoryId;
    private String title;
    private String thumbnail;
    private Integer likesCount;
    private Integer viewsCount;
    private Integer repliesCount;
    private LocalDateTime createdAt;
    private Boolean isPrivate;

    public static PostPreviewDto of (Post post){
        return new PostPreviewDto(
                post.getBlogUrl(),
                post.getId(),
                post.getCategory().getId(),
                post.getTitle(),
                post.getThumbnail(),
                post.getLikesCount(),
                post.getViewsCount(),
                0,
                post.getCreatedAt(),
                post.getIsPrivate());
    }
}
