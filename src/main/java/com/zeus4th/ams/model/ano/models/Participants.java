package com.zeus4th.ams.model.ano.models;

import javax.persistence.*;

@Entity
@Table(name = "participants")
public class Participants {

    @Id
    @Column(name = "participant_id", unique = true, nullable = false)
    private String participantId; // its just sum of userId+sessionId

    @Column(name = "userId",nullable = false)
    private String userId;

    @Column(name = "sessionId",nullable = false)
    private String sessionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "session_details_participants_list",nullable = false)
    private SessionDetails sessionDetails;

    public Participants() { }

    public Participants(String participantId, String userId, String sessionId) {
        this.participantId = participantId;
        this.userId = userId;
        this.sessionId = sessionId;
        sessionDetails = new SessionDetails();
        sessionDetails.setSessionId(sessionId);
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
