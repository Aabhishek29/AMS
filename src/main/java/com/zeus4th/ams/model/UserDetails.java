package com.zeus4th.ams.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.model.ano.models.SessionUserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userDetails")
public class UserDetails {

    @Id
    @Column(name = "sessionListId",unique = true,nullable = false)
    private String sessionListId; // primary key

    @Column(name = "created_at",nullable = false)
    private String createdAt;
    @Column(name = "updated_at",nullable = false)
    private String updatedAt;


    // appId define as "AMS-ANO", "AMS-Upcoming...",etc.
    @Column(name = "appId", nullable = false)
    private String appId;

    @Column(name = "status", nullable = true, unique = false)
    private String status;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "deactivate_user")
    private Boolean deactivateUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "session_user_details",nullable = false)
    @JsonBackReference
    private SessionUserDetails sessionUserDetails;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    @JsonBackReference
    private User user;

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public SessionUserDetails getSessionUserDetails() {
        return sessionUserDetails;
    }

    public void setSessionUserDetails(SessionUserDetails sessionUserDetails) {
        this.sessionUserDetails = sessionUserDetails;
    }

    public UserDetails(String sessionListId, String createdAt, String updatedAt, String appId, String status, String pofileUrl, Boolean deactivateUser, String userId) {
        this.sessionListId = sessionListId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.appId = appId;
        this.status = status;
        this.profileUrl = pofileUrl;
        this.deactivateUser = deactivateUser;
        user =new User();
        user.setUserId(userId);
    }

    public UserDetails() {

    }

    public Boolean getDeactivateUser() {
        return deactivateUser;
    }

    public void setDeactivateUser(Boolean deactivateUser) {
        this.deactivateUser = deactivateUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
        return profileUrl;
    }

    public void setPofileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }




}
