package io.traveler.travel.trip.service.impl;


import io.traveler.travel.trip.dto.input.*;
import io.traveler.travel.trip.dto.response.*;
import io.traveler.travel.trip.repository.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import io.traveler.travel.trip.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {
    //TODO: QueryDsl 고려
    private final MessageRepository;


    @Override
    public Slice<MessageResponse> getMessage(GetMessageInput input) {
        return null;
    }

    @Override
    public void registerMessage(CreateMessageInput input) {

    }

    @Override
    public void modifyMessage(UpdateMessageInput input) {

    }

    @Override
    public void removeMessage(long tripId, long messageId, String username) {

    }

}
