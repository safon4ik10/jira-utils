package ru.rvision.utils.services;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import ru.rvision.utils.model.Message;

/**
 * @author Vladimir Troshin on 29.06.2022
 * https://jira.rvision.pro/browse/
 */
public class RocketSenderClientResponse {
    private boolean success;
    private String id;
    private String value;
    private String error;
    private Message[] messages;
    private Message message;

    public boolean hasId() {
        return this.id != null;
    }

    @JsonSetter("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonGetter("_id")
    public String getId() {
        return this.id;
    }

    public boolean hasValue() {
        return this.value != null;
    }

    @JsonGetter("value")
    public String getValue() {
        return value;
    }

    @JsonSetter("value")
    public void setValue(String value) {
        this.value = value;
    }

    public void setSuccess(boolean result) {
        this.success = result;
    }

    public boolean isSuccessful() {
        return this.success;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public boolean hasAnError() {
        return !this.error.isEmpty();
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public Message[] getMessages() {
        return this.messages;
    }

    public boolean hasMessages() {
        return this.messages != null;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }

    public boolean hasMessage() {
        return this.message != null;
    }

}
