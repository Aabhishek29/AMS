package com.zeus4th.ams.model.ano.models;

import com.zeus4th.ams.model.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "anoy_users")
public class AnoyUser {


    @Id
    @Column(name = "userId",unique = true,nullable = false)
    private String userId;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<UserDetails> userDetails = new ArrayList<>();

    @Column(name = "user_name", length = 50, unique = true,nullable = false)
    private  String userName;

    @Column(name = "created_at", nullable = false)
    private  String createdAt ;

    @Column(name = "updated_at", nullable = false)
    private  String updatedAt;

    @Column(name = "profile_url")
    private  String profileUrl ;

    // authenticated indicate that user is granted by AMS portal
    @Column(name = "authenticated", nullable = false)
    private Boolean authenticated ;

    @Column(name = "client_token",nullable = false)
    private String clientToken;


    public AnoyUser() { }

    public AnoyUser(String userId, List<UserDetails> userDetails, String userName, String createdAt, String updatedAt, String profileUrl, Boolean authenticated, String clientToken) {
        this.userId = userId;
        this.userDetails = userDetails;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profileUrl = profileUrl;
        this.authenticated = authenticated;
        this.clientToken = clientToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UserDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }
}