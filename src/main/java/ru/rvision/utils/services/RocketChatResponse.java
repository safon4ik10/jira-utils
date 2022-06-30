package ru.rvision.utils.services;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import ru.rvision.utils.model.Message;

/**
 * @author Vladimir Troshin on 30.06.2022
 * https://jira.rvision.pro/browse/
 */

public class RocketChatResponse {
    private String ts;
    private String channel;
    private Message message;
    private boolean success;

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @JsonGetter("channel")
    public String getChannel() {
        return channel;
    }

    @JsonSetter("channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
