package net.andreanunez.encuestabackend.services;

import net.andreanunez.encuestabackend.models.requests.PollReplyRequestModel;

public interface PollReplyService {
    public long createPollReply(PollReplyRequestModel model);
}
