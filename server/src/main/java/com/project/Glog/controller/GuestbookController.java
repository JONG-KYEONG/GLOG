package com.project.Glog.controller;

import com.project.Glog.dto.request.guestbook.GuestbookMessageRequest;
import com.project.Glog.dto.response.guestbook.GuestbookResponse;
import com.project.Glog.security.CurrentUser;
import com.project.Glog.security.UserPrincipal;
import com.project.Glog.service.GuestbookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
public class GuestbookController {
    private final GuestbookService guestbookService;

    @PostMapping("/guestbook")
    ResponseEntity<String> saveMessage(@CurrentUser UserPrincipal userPrincipal, @RequestBody GuestbookMessageRequest req){
        try{
            guestbookService.save(userPrincipal, req);
            return new ResponseEntity<>("success save guestbook message", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("failed save guestbook message", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping ("/guestbook")
    ResponseEntity<String> updateMessage(@CurrentUser UserPrincipal userPrincipal, @RequestBody GuestbookMessageRequest req){
        try{
            guestbookService.update(userPrincipal, req);
            return new ResponseEntity<>("success update guestbook message", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("failed update guestbook message", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping ("/guestbook")
    ResponseEntity<GuestbookResponse> readMessage(@CurrentUser UserPrincipal userPrincipal, @RequestParam Long blogId){
            GuestbookResponse res = guestbookService.read(userPrincipal, blogId);
            return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping ("/guestbook")
    ResponseEntity<String> deleteMessage(@CurrentUser UserPrincipal userPrincipal, @RequestParam Long messageId){
        try{
            guestbookService.delete(userPrincipal, messageId);
            return new ResponseEntity<>("success delete guestbook message", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("not your message", HttpStatus.UNAUTHORIZED);
        }
    }
}
