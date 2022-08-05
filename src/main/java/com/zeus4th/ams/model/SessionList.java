package com.zeus4th.ams.model;

import javax.persistence.*;

@Table(name = "sessionList")
@IdClass(UserDetails.class)
public class SessionList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Id
    @Column(name = "session_list_id",nullable = false)
    private String sessionListId;

    @Column(name = "session_id",nullable = false,unique = true)
    private String sessionId;

    public SessionList(){

    }

    public SessionList(long id, String sessionId) {
        this.id = id;
        this.sessionId = sessionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
