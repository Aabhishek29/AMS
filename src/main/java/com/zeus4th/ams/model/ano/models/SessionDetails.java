package com.zeus4th.ams.model.ano.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chatSessionDetails")
public class SessionDetails {

    @Id()
    @Column(name = "chat_message_id", unique = true,nullable = false)
    private String chatMessageId;

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

    @OneToOne(mappedBy = "sessionDetails")
    private SessionList sessionList;

    @OneToMany(mappedBy = "sessionDetails",fetch = FetchType.LAZY)
    private List<ChatMessages> chatMessagesId = new ArrayList<>();

    public List<ChatParticipants> getChatPaticipantsList() {
        return chatParticipantsList;
    }

    public void setChatPaticipantsList(List<ChatParticipants> chatParticipantsList) {
        this.chatParticipantsList = chatParticipantsList;
    }

    @OneToMany(mappedBy = "sessionDetails",fetch = FetchType.LAZY)
    private List<ChatParticipants> chatParticipantsList = new ArrayList<>();

    public String getChatMessageId() {
        return chatMessageId;
    }

    public void setChatMessageId(String chatMessageId) {
        this.chatMessageId = chatMessageId;
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

    public SessionList getSessionList() {
        return sessionList;
    }

    public void setSessionList(SessionList sessionList) {
        this.sessionList = sessionList;
    }

    public List<ChatMessages> getChatMessagesId() {
        return chatMessagesId;
    }

    public void setChatMessagesId(List<ChatMessages> chatMessagesId) {
        this.chatMessagesId = chatMessagesId;
    }

    // group profile contains url only if chat_type == "group"


}
