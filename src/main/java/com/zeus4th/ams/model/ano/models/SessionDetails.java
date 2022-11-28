package com.zeus4th.ams.model.ano.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zeus4th.ams.model.User;
import com.zeus4th.ams.model.UserDetails;
import kotlin.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "sessionDetails")
public class SessionDetails {

    @Id()
    @Column(name = "sessionid", unique = true,nullable = false)
    private String sessionId;

    @Column(name = "created_at",nullable = false)
    private String createdAt;

    @Column(name = "updated_at",nullable = false)
    private String updatedAt;

    // chat type basically indiacates that its group chat or personal chat
    @Column(name = "chat_type")
    private String chatType; // ----> group / personal

    @Column(name = "group_profile_url")
    private String groupProfileUrl;

    // connection type is link phone_number/organization email
    @Column(name = "connectionType")
    private String connectionType;

    @Column(name = "creater", nullable = false)
    private String creator;    // stores userID of owner

    @OneToMany(mappedBy = "sessionDetails",fetch = FetchType.LAZY)
    private List<Participants> participantsList = new ArrayList<>();

    @OneToMany(mappedBy = "sessionDetails",fetch = FetchType.LAZY)
    private List<ChatMessages> chatMessagesId = new ArrayList<>();

    @ManyToMany(targetEntity = UserDetails.class)
    @JsonBackReference
    private List<UserDetails> userDetails = new ArrayList<>();


    public SessionDetails() {
    }

    public SessionDetails(String sessionId, String createdAt, String updatedAt, String chatType, String groupProfileUrl, String connectionType, String creator) {
        this.sessionId = sessionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.chatType = chatType;
        this.groupProfileUrl = groupProfileUrl;
        this.connectionType = connectionType;
        this.creator = creator;;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getGroupProfileUrl() {
        return groupProfileUrl;
    }

    public void setGroupProfileUrl(String groupProfileUrl) {
        this.groupProfileUrl = groupProfileUrl;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Participants> getParticipantsList() {
        return participantsList;
    }

    public void setParticipantsList(List<Participants> participantsList) {
        this.participantsList = participantsList;
    }

    public List<ChatMessages> getChatMessagesId() {
        return chatMessagesId;
    }

    public void setChatMessagesId(List<ChatMessages> chatMessagesId) {
        this.chatMessagesId = chatMessagesId;
    }

    public List<UserDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }
}
