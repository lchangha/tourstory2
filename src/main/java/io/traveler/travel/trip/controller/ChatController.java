package io.traveler.travel.trip.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.traveler.travel.trip.service.ChatService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/trip/{tripId}/message")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("{messageId}")
    public MessageResponse getMessage(@PathVariable long tripId, @PathVariable long messageId) {
        return new String();
    }

    @PostMapping("{messageId}")
    public void createMessage(@PathVariable long tripId, @PathVariable long messageId, @RequestBody @Valid createMessageRequest) {
        //TODO: 인증된 유저 정보를 가져올것
    }
    
    @PutMapping("{messageId}")
    public void modifyMessage(@PathVariable long tripId, @PathVariable long messageId, @RequestBody UpdateMessageRequest) {
        //TODO: 인증된 유저 정보를 가져올것
    }

    @DeleteMapping
    public void removeMessage(@PathVariable long tripId, @PathVariable long messageId) {
        //TODO: 인증된 유저 정보를 가져올것
    }





}
