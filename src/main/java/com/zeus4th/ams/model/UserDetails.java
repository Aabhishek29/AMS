package com.zeus4th.ams.model;

import com.zeus4th.ams.model.ano.models.SessionList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "userDetails")
public class UserDetails {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true,nullable = false)
    private String id;

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
    private String pofileUrl;

    @Column(name = "deactivate_user")
    private Boolean deactivateUser;

    @OneToMany(mappedBy = "userDetails", fetch = FetchType.LAZY)
    private List<SessionList> listSessions = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;

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

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
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

    public List<SessionList> getListSessions() {
        return listSessions;
    }

    public void setListSessions(List<SessionList> listSessions) {
        this.listSessions = listSessions;
    }



}
