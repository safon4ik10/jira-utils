package ru.rvision.utils.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vladimir Troshin on 29.06.2022
 * https://jira.rvision.pro/browse/
 */
public class Attachments {
    private String audioUrl;
    private String authorIcon;
    private String authorLink;
    private String authorName;
    private boolean collapsed;
    private String color;
    private String imageUrl;
    private String messageLink;
    private String text;
    private String thumbUrl;
    private String title;
    private String titleLink;
    private boolean titleLinkDownload;
    private String ts;
    private String videoUrl;
    private List<AttachmentsField> fields;

    public Attachments() {
        this.fields = new ArrayList<AttachmentsField>();
    }

    public Attachments(String text) {
        this.fields = new ArrayList<AttachmentsField>();
        this.text = text;
    }

    public Attachments(String title, String text) {
        this.fields = new ArrayList<AttachmentsField>();
        this.title = title;
        this.text = text;
    }

    public List<AttachmentsField> getFields() {
        return fields;
    }

    public void setFields(AttachmentsField[] field) {
        this.fields = new ArrayList<>(Arrays.asList(field));
    }


    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAuthorIcon() {
        return authorIcon;
    }

    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @JsonGetter("collapsed")
    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMessageLink() {
        return messageLink;
    }

    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public boolean isTitleLinkDownload() {
        return titleLinkDownload;
    }

    public void setTitleLinkDownload(boolean titleLinkDownload) {
        this.titleLinkDownload = titleLinkDownload;
    }

    @JsonGetter("ts")
    public String getTs() {
        return ts;
    }

    @JsonSetter("ts")
    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public static final class AttachmentsBuilder {
        private Attachments attachments;

        private AttachmentsBuilder() {
            attachments = new Attachments();
        }

        public static AttachmentsBuilder builder() {
            return new AttachmentsBuilder();
        }

        public AttachmentsBuilder withAudioUrl(String audioUrl) {
            attachments.setAudioUrl(audioUrl);
            return this;
        }

        public AttachmentsBuilder withAuthorIcon(String authorIcon) {
            attachments.setAuthorIcon(authorIcon);
            return this;
        }

        public AttachmentsBuilder withAuthorLink(String authorLink) {
            attachments.setAuthorLink(authorLink);
            return this;
        }

        public AttachmentsBuilder withAuthorName(String authorName) {
            attachments.setAuthorName(authorName);
            return this;
        }

        public AttachmentsBuilder withCollapsed(boolean collapsed) {
            attachments.setCollapsed(collapsed);
            return this;
        }

        public AttachmentsBuilder withColor(String color) {
            if (!color.startsWith("#")){
                color = "#" + color;
            }
            attachments.setColor(color);
            return this;
        }

        public AttachmentsBuilder withImageUrl(String imageUrl) {
            attachments.setImageUrl(imageUrl);
            return this;
        }

        public AttachmentsBuilder withMessageLink(String messageLink) {
            attachments.setMessageLink(messageLink);
            return this;
        }

        public AttachmentsBuilder withText(String text) {
            attachments.setText(text);
            return this;
        }

        public AttachmentsBuilder withThumbUrl(String thumbUrl) {
            attachments.setThumbUrl(thumbUrl);
            return this;
        }

        public AttachmentsBuilder withTitle(String title) {
            attachments.setTitle(title);
            return this;
        }

        public AttachmentsBuilder withTitleLink(String titleLink) {
            attachments.setTitleLink(titleLink);
            return this;
        }

        public AttachmentsBuilder withTitleLinkDownload(boolean titleLinkDownload) {
            attachments.setTitleLinkDownload(titleLinkDownload);
            return this;
        }

        public AttachmentsBuilder withTs(String ts) {
            attachments.setTs(ts);
            return this;
        }

        public AttachmentsBuilder withVideoUrl(String videoUrl) {
            attachments.setVideoUrl(videoUrl);
            return this;
        }

        public AttachmentsBuilder withFields(AttachmentsField[] fields) {
            attachments.setFields( fields);
            return this;
        }

        public Attachments build() {
            return attachments;
        }
    }
}
