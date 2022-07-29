package com.zeus4th.ams.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserSignUp {


    private final String name;
    private final String userName;
    private final String email;
    private final String password;
    private final long phone;
    private final String organizationEmail;
    private String updatedAt;
    private String createdAt;
    private final String profileUrl;
    private final Boolean authenticated;


    public UserSignUp(String userName, String name, String email, String password, long phone, String organizationEmail, String createdAt, String updatedAt, String profileUrl, Boolean authenticated) {

        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.organizationEmail = organizationEmail;
        this.updatedAt = getCurrentTime();
        this.createdAt = getCurrentTime();
        this.profileUrl = profileUrl;
        this.authenticated = authenticated;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getPhone() {
        return phone;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public String getUpdatedAt() {
        this.updatedAt = getCurrentTime();
        return updatedAt;
    }

    public String getCreatedAt() {
        this.createdAt = getCurrentTime();
        return createdAt;
    }
    public String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String newdate = dtf.format(now);
        return newdate;
    }

}
