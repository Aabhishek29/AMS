package com.zeus4th.ams.model.ano.models;

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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Participants> participantsList = new ArrayList<>();

    @OneToMany(mappedBy = "sessionDetails",fetch = FetchType.LAZY)
    private List<ChatMessages> chatMessagesId = new ArrayList<>();

    @ManyToOne
    private UserDetails userDetails;


    public SessionDetails() {
    }

    public SessionDetails(String sessionId, String createdAt, String updatedAt, String chatType, String groupProfileUrl, String connectionType, String creator, List<Participants> participantsList, UserDetails userDetails) {
        this.sessionId = sessionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.chatType = chatType;
        this.groupProfileUrl = groupProfileUrl;
        this.connectionType = connectionType;
        this.creator = creator;
        this.participantsList = participantsList;
        this.userDetails = userDetails;
    }
}
