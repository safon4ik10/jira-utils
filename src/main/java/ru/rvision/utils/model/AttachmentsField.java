package ru.rvision.utils.model;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * @author Vladimir Troshin on 29.06.2022
 * https://jira.rvision.pro/browse/
 */
public class AttachmentsField {
    private boolean isShort;
    private String title;
    private String value;


    @JsonGetter("short")
    public boolean isShort() {
        return this.isShort;
    }

    public void setShort(boolean aShort) {
        this.isShort = aShort;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
