package com.skypro.course_03.dto;

public class AvatarDto {

    private Long id;
    private long fileSize;
    private String mediaType;
    private String url;

    public AvatarDto(Long id, Long fileSize, String mediaType, String url) {
        this.id = id;
        this.fileSize = fileSize;
        this.mediaType = mediaType;
        this.url = url;
    }

    public AvatarDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
