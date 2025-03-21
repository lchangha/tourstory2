package io.traveler.travel.trip.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.traveler.travel.trip.dto.request.UpdateMessageRequest;
import io.traveler.travel.trip.dto.input.CreateMessageInput;
import io.traveler.travel.trip.dto.input.GetMessageInput;
import io.traveler.travel.trip.dto.input.UpdateMessageInput;
import io.traveler.travel.trip.dto.request.CreateMessageRequest;
import io.traveler.travel.trip.dto.response.MessageResponse;
import io.traveler.travel.trip.service.ChatService;
import jakarta.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Slice<MessageResponse> getMessage(@PathVariable long tripId,
                                             @PathVariable long messageId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "20") int size) {
        GetMessageInput input =  GetMessageInput.of(tripId, messageId, PageRequest.of(page, size));
        return chatService.getMessage(input);
    }

    @PostMapping("{messageId}")
    public void createMessage(@PathVariable long tripId,
                              @PathVariable long messageId,
                              @AuthenticationPrincipal UserDetails user,
                              @RequestBody @Valid CreateMessageRequest createMessageRequest) {

        CreateMessageInput input = CreateMessageInput.of(tripId, messageId, user.getUsername(), createMessageRequest.message()); 
        chatService.registerMessage(input);
    }   
    
    @PutMapping("{messageId}")
    public void modifyMessage(@PathVariable long tripId,
                              @PathVariable long messageId,
                              @AuthenticationPrincipal UserDetails user,
                              @RequestBody UpdateMessageRequest updateMessageRequest) {
        UpdateMessageInput input = UpdateMessageInput.of(tripId, messageId, user.getUsername(), updateMessageRequest.message());
        chatService.modifyMessage(input);
    }

    @DeleteMapping("{messageId}")
    public void removeMessage(@PathVariable long tripId,
                              @PathVariable long messageId,
                              @AuthenticationPrincipal UserDetails user) {
        chatService.removeMessage(tripId, messageId, user.getUsername());
    }





}
