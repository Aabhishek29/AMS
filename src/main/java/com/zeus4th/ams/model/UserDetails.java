package com.zeus4th.ams.model;

import javax.persistence.*;

@Table(name = "userDetails")
public class UserDetails {

    @Column(name = "uid",unique = true,nullable = false)
    private String uid;

    @Id
    @Column(name = "session_list_id",unique = true,nullable = false)
    private String sessionListId;

    @Column(name = "created_at",nullable = false)
    private String createdAt;
    @Column(name = "updated_at",nullable = false)
    private String updatedAt;

    @Column(name = "status", nullable = true, unique = false)
    private String status;

    @Column(name = "profile_url")
    private String pofileUrl;

    public UserDetails() {
    }

    public UserDetails(String uid, String sessionListId, String createdAt, String updatedAt, String status, String pofileUrl) {
        this.uid = uid;
        this.sessionListId = sessionListId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.pofileUrl = pofileUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSessionListId() {
        return sessionListId;
    }

    public void setSessionListId(String sessionListId) {
        this.sessionListId = sessionListId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPofileUrl() {
        return pofileUrl;
    }

    public void setPofileUrl(String pofileUrl) {
        this.pofileUrl = pofileUrl;
    }
}
