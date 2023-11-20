package com.project.Glog.controller;


import com.project.Glog.domain.Post;
import com.project.Glog.dto.PostPreviewDtos;
import com.project.Glog.dto.request.post.PostCreateRequest;
import com.project.Glog.dto.request.post.PostUpdateRequest;
import com.project.Glog.dto.response.post.PostPreviewResponse;
import com.project.Glog.dto.response.post.PostReadResponse;
import com.project.Glog.security.CurrentUser;
import com.project.Glog.security.UserPrincipal;
import com.project.Glog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PostController {
    @Autowired
    private PostService postService;


    @RequestMapping(value = "/post",
            method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Long> create(@CurrentUser UserPrincipal userPrincipal,
                                       @RequestParam(value = "thumbnail", required = false) MultipartFile multipartFile,
                                       @RequestPart PostCreateRequest postCreateRequest) throws IOException {

        Post post = postService.create(userPrincipal, multipartFile, postCreateRequest);

        return new ResponseEntity<>(post.getId(), HttpStatus.OK);
    }

    @PutMapping("/post")
    public ResponseEntity<Long> update(@CurrentUser UserPrincipal userPrincipal,
                                       @RequestPart(value="thumbnail", required = false) MultipartFile multipartFile,
                                       @RequestPart PostCreateRequest postCreateRequest) throws IOException {

        Post post = postService.update(userPrincipal, multipartFile, postCreateRequest);

        return new ResponseEntity<>(post.getId(), HttpStatus.OK);
    }

    @DeleteMapping ("/post")
    public ResponseEntity<String> delete(@CurrentUser UserPrincipal userPrincipal,
                                         @RequestParam Long postId){

        try {
            postService.delete(userPrincipal, postId);
            return new ResponseEntity<>("success delete post",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/post")
    public ResponseEntity<?> readPost(@CurrentUser UserPrincipal userPrincipal, @RequestParam Long postId){

        PostReadResponse postReadResponse = new PostReadResponse();
        try{
                postReadResponse = postService.readPost(userPrincipal, postId);
        }
        catch(Exception e){
            return new ResponseEntity<>("no post", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(postReadResponse, HttpStatus.OK);
    }

    @GetMapping("/collect")
    public ResponseEntity<PostPreviewResponse> collect(@RequestParam int page){

        PostPreviewResponse postPreviewResponse = postService.getCollect(page-1);

        return new ResponseEntity<>(postPreviewResponse,HttpStatus.OK);
    }
    @GetMapping("/post/previews/{kind}")
    public ResponseEntity<PostPreviewDtos> collect(@PathVariable String kind,
                                                       @RequestParam int page){

        PostPreviewDtos previews = postService.getPreviews(kind, page-1);

        return new ResponseEntity<>(previews,HttpStatus.OK);
    }

    @GetMapping("/search/content")
    public ResponseEntity<PostPreviewDtos> searchContentsByContent(@RequestParam String content){
        //content 내용을 포함한 게시글의 리스트를 생성한다.
        PostPreviewDtos postPreviewDtos = postService.searchPostsByContent(content);

        return new ResponseEntity<>(postPreviewDtos, HttpStatus.OK);
    }

    @GetMapping("/search/hashtag")
    public ResponseEntity<PostPreviewDtos> searchContentsByHashtag(@RequestParam String hashtag){
        //hashtag 내용을 포함한 게시글의 리스트를 생성한다
        PostPreviewDtos postPreviewDtos = postService.searchPostsByHashtag(hashtag);

        return new ResponseEntity<>(postPreviewDtos, HttpStatus.OK);
    }
    @GetMapping("/search/title")
    public ResponseEntity<PostPreviewDtos> searchContentsByTitle(@RequestParam String title){
        //content 내용을 포함한 게시글의 리스트를 생성한다.
        PostPreviewDtos postPreviewDtos = postService.searchPostsByTitle(title);

        return new ResponseEntity<>(postPreviewDtos, HttpStatus.OK);
    }
    @GetMapping("/search/user")
    public ResponseEntity<PostPreviewDtos> searchContentsByUser(@RequestParam String nickname){
        //content 내용을 포함한 게시글의 리스트를 생성한다.
        PostPreviewDtos postPreviewDtos = postService.searchPostsByUser(nickname);

        return new ResponseEntity<>(postPreviewDtos, HttpStatus.OK);
    }

    @PatchMapping ("/post/like")
    public ResponseEntity<String> plusLike(@CurrentUser UserPrincipal userPrincipal,
                                            @RequestParam Long postId){

        String result = postService.clickLike(userPrincipal, postId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

