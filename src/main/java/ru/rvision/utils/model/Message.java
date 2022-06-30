package ru.rvision.utils.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.vdurmont.emoji.Emoji;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Vladimir Troshin on 29.06.2022
 * https://jira.rvision.pro/browse/
 */

public class Message {
    private String alias;
    private String avatar;
    private Emoji emoji;
    private String roomId;
    private String channel;
    private String text;
    private ArrayList<Attachments> attachments;
    private boolean parseUrl;
    private boolean groupable;
    private String ts;

    public Message() {
        this.text = "";
        this.attachments = new ArrayList<Attachments>();
    }

    public Message(String message) {
        this.text = message;
        this.attachments = new ArrayList<Attachments>();
    }

    public Message(String roomId, String text){
        this.roomId = roomId;
        this.text = text;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @JsonGetter("alias")
    public String getAlias() {
        return alias;
    }

    @JsonSetter("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    @JsonGetter("rid")
    public String getRoomId() {
        return roomId;
    }

    @JsonSetter("rid")
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @JsonGetter("msg")
    public String getText() {
        return text;
    }

    @JsonSetter("msg")
    public void setText(String text) {
        this.text = text;
    }

    public Attachments[] getAttachments() {
        return this.attachments.toArray(new Attachments[this.attachments.size()]);
    }

    public void addAttachment(Attachments attachment) {
        this.attachments.add(attachment);
    }

    public void setAttachments(Attachments[] attachments) {
        this.attachments = new ArrayList<Attachments>(Arrays.asList(attachments));
    }

    @JsonGetter("parseUrls")
    public boolean isParseUrl() {
        return parseUrl;
    }

    @JsonSetter("parseUrls")
    public void setParseUrl(boolean parseUrl) {
        this.parseUrl = parseUrl;
    }

    public boolean isGroupable() {
        return groupable;
    }

    public void setGroupable(boolean groupable) {
        this.groupable = groupable;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Message{" +
                "alias='" + alias + '\'' +
                ", avatar='" + avatar + '\'' +
                ", emoji=" + emoji +
                ", roomId='" + roomId + '\'' +
                ", text='" + text + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
