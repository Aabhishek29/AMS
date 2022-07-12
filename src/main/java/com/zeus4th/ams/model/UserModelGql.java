package com.zeus4th.ams.model;

import javax.persistence.*;

@Table
@Entity
public class UserModelGql {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String userId;

    public String name;
    public String pswd;
    public long user_ph_no;
    public String email;
    public String organization_email;
    public String createdAt;
    public String updatedAt;
    public String profile_url;
}
