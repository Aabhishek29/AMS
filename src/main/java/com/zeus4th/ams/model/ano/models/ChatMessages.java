package com.zeus4th.ams.model.ano.models;

import javax.persistence.*;

@Entity
@Table(name = "chatMessage")
public class ChatMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long messageId;
    @Column(name = "message",nullable = true)
    private String message;
    @Column(name = "mimeType",nullable = false)
    private String mimeType;
    @Column(name = "created_at",nullable = false)
    private String createdAt;
    @Column(name = "sender",nullable = false)
    private String sender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "session_details_chat_message_id", nullable = false)
    private SessionDetails sessionDetails;

    public SessionDetails getSessionDetails() {
        return sessionDetails;
    }

    public void setSessionDetails(SessionDetails sessionDetails) {
        this.sessionDetails = sessionDetails;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
