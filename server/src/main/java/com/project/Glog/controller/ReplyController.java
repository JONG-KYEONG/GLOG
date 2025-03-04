package com.project.Glog.controller;

import com.project.Glog.dto.request.reply.ReplyCreateRequest;
import com.project.Glog.dto.request.reply.ReplyGetRequest;
import com.project.Glog.dto.request.reply.ReplyUpdateRequest;
import com.project.Glog.dto.response.reply.ReplyGetResponse;
import com.project.Glog.security.CurrentUser;
import com.project.Glog.security.UserPrincipal;
import com.project.Glog.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;


    @PostMapping("/replies")
    public ResponseEntity<Long> create(@CurrentUser UserPrincipal userPrincipal,
                                       @RequestBody ReplyCreateRequest replyCreateRequest) {

        Long postId = replyService.createReply(userPrincipal, replyCreateRequest);

        return new ResponseEntity<>(postId, HttpStatus.OK);
    }

    @GetMapping("/replies")
    public ResponseEntity<ReplyGetResponse> read(@CurrentUser UserPrincipal userPrincipal,
                                                 @RequestParam Long postId,
                                                 @RequestParam int page,
                                                 @RequestParam String order) {

        ReplyGetRequest replyGetRequest = new ReplyGetRequest(postId, page, order);
        ReplyGetResponse replyGetReponse = replyService.getReplies(userPrincipal, replyGetRequest);

        return new ResponseEntity<>(replyGetReponse, HttpStatus.OK);
    }

    @PutMapping("/replies")
    public ResponseEntity<String> update(@CurrentUser UserPrincipal userPrincipal,
                                         @RequestBody ReplyUpdateRequest replyupdateRequest)  {

        try{
            replyService.updateReply(userPrincipal, replyupdateRequest);
        }
        catch (Exception e){
            return new ResponseEntity<>("not owner", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("success update reply", HttpStatus.OK);
    }

    @DeleteMapping("/replies")
    public ResponseEntity<String> delete(@CurrentUser UserPrincipal userPrincipal,
                                         @RequestParam Long replyId) {

        try{
            replyService.deleteReply(userPrincipal, replyId);
        }
        catch (Exception e){
            return new ResponseEntity<>("not owner", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("success delete reply", HttpStatus.OK);
    }

    @PatchMapping("/replies/like/{replyId}")
    public ResponseEntity<String> clickLike(@CurrentUser UserPrincipal userPrincipal,
                                         @PathVariable Long replyId) {

        String result = replyService.clickLike(userPrincipal, replyId);

        if (result.equals("remove")){
            return new ResponseEntity<>("success remove like", HttpStatus.OK);
        }

        return new ResponseEntity<>("success add like", HttpStatus.OK);
    }
}
