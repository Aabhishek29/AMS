package com.zeus4th.ams.model.ano.models;

import com.zeus4th.ams.model.UserDetails;

import javax.persistence.*;

@Entity
@Table(name = "sessionList")
public class SessionList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "session_id",nullable = false,unique = true)
    private String sessionId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_details_uid", nullable = false)
    private UserDetails userDetails;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinTable(name = "session_list_session_details",
            joinColumns = @JoinColumn(name = "session_list_null"),
            inverseJoinColumns = @JoinColumn(name = "session_details_cid"))
    private SessionDetails sessionDetails;

    public SessionDetails getSessionDetails() {
        return sessionDetails;
    }

    public void setSessionDetails(SessionDetails sessionDetails) {
        this.sessionDetails = sessionDetails;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
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
