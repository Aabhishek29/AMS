package com.zeus4th.ams.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "chatSessionDetails")
public class SessionDetails {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    @Column(name = "created_at",nullable = false)
    private String createdAt;
    @Column(name = "updated_at",nullable = false)
    private String updatedAt;
    @Column(name = "chat_type")
    private String chatType;
    @Column(name = "group_profile_url")
    private String groupProfileUrl;
    @Column(name = "connectionType")
    // connection type is link phone number/email/organization email
    private String connectionType;


}
