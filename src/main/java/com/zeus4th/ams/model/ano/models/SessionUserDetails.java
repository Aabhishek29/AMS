package com.zeus4th.ams.model.ano.models;

import com.zeus4th.ams.model.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SessionUserDetails")
public class SessionUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Number id;

    @OneToMany(mappedBy = "sessionUserDetails", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserDetails> userDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private SessionDetails sessionDetails;

    public SessionUserDetails(List<UserDetails> userDetails, SessionDetails sessionDetails) {
        this.userDetails = userDetails;
        this.sessionDetails = sessionDetails;
    }

    public SessionUserDetails() { }

    public void setId(Number id) {
        this.id = id;
    }

    public void setUserDetails(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }

    public void setSessionDetails(SessionDetails sessionDetails) {
        this.sessionDetails = sessionDetails;
    }

    public Number getId() {
        return id;
    }

    public List<UserDetails> getUserDetails() {
        return userDetails;
    }

    public SessionDetails getSessionDetails() {
        return sessionDetails;
    }
}
