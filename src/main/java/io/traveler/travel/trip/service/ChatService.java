package io.traveler.travel.trip.service;

import org.springframework.data.domain.Slice;

import io.traveler.travel.trip.dto.input.CreateMessageInput;
import io.traveler.travel.trip.dto.input.GetMessageInput;
import io.traveler.travel.trip.dto.input.UpdateMessageInput;
import io.traveler.travel.trip.dto.response.MessageResponse;

public interface ChatService {
    Slice<MessageResponse> getMessage(GetMessageInput input);

    void registerMessage(CreateMessageInput input);

    void modifyMessage(UpdateMessageInput input);

    void removeMessage(long tripId, long messageId, String username);
}
