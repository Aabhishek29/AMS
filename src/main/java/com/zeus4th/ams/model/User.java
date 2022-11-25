package com.zeus4th.ams.model;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "userId",unique = true,nullable = false)
    private String userId;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<UserDetails> userDetails = new ArrayList<>();

    @Column(name = "user_name", length = 50, unique = true,nullable = false)
    private  String userName;
    @Column(name = "name", length = 50, nullable = false)
    private  String name;
    @Column(name = "email", length = 80, unique = true)
    private  String email ;
    @Column(name = "password", length = 16, nullable = false)
    private  String password ;
    @Column(name = "phone", unique = true, length = 10)
    private  long phone ;
    @Column(name = "organization_email", length = 80)
    private  String organizationEmail ;
    @Column(name = "created_at", nullable = false)
    private  String createdAt ;
    @Column(name = "updated_at", nullable = false)
    private  String updatedAt;
    @Column(name = "profile_url")
    private  String profileUrl ;

    // authenticated indicate that user is granted by AMS portal
    @Column(name = "authenticated", nullable = false)
    private Boolean authenticated ;

    // Super User for AMS portal
    @Column(name = "super_user")
    private Boolean superUser;

    public User() {

    }

//    public User( String userName,String name, String email, String password, long phone, String organizationEmail, String profileUrl, Boolean authenticated) {
//
//        this.userName = userName;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.phone = phone;
//        this.organizationEmail = organizationEmail;
//        this.createdAt = getCurrentTime();
//        this.updatedAt = getCurrentTime();
//        this.profileUrl = profileUrl;
//        this.authenticated = authenticated;
//    }
    public User( String userId, String userName,String name, String email, String password, long phone, String organizationEmail,
                 String createdAt,String updatedAt, String profileUrl, Boolean authenticated, Boolean superUser) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.organizationEmail = organizationEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profileUrl = profileUrl;
        this.authenticated = authenticated;
        this.superUser = superUser;
    }

    public List<UserDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        String newdate = dtf.format(now);
        this.createdAt =createdAt;

    }

    public String getUpdatedAt() {


        return updatedAt;
    }

    public void setUpdatedAt() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String newdate = dtf.format(now);
        this.updatedAt = newdate;


    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getSuperUser() {
        return superUser;
    }

    public void setSuperUser(Boolean superUser) {
        this.superUser = superUser;
    }

    //    public class LastUpdateListener {
//        /**
//         * automatic property set before any database persistence
//         */
//        @PreUpdate
//        @PrePersist
//        public void setLastUpdate(User o) {
//            o.setAuthenticated(true );
//        }
//    }

//    public String getCurrentTime(){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        String newdate = dtf.format(now);
//        return newdate;
//    }

    @PrePersist
    public void initializedUuid() {
        this.userId = UUID.randomUUID().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String newdate = dtf.format(now);
        this.createdAt = newdate;
        this.updatedAt = newdate;
        this.authenticated =true;
        this.superUser = false;
    }

}
