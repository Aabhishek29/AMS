package com.zeus4th.ams.model.ano.models;

import javax.persistence.*;

@Entity
@Table(name = "chat_participants")
public class ChatPaticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pid;

    @Column(name = "participant",nullable = false)
    private String participant;

    @ManyToOne
    @JoinColumn(name = "session_details_chat_message_id")
    private SessionDetails sessionDetails;

    public SessionDetails getSessionDetails() {
        return sessionDetails;
    }

    public void setSessionDetails(SessionDetails sessionDetails) {
        this.sessionDetails = sessionDetails;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
}
