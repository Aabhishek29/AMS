package com.zeus4th.ams.model;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
    @Column(name = "organization_email", unique = true, length = 80)
    private  String organizationEmail ;
    @Column(name = "created_at", nullable = false)
    private  String createdAt ;
    @Column(name = "updated_at", nullable = false)
    private  String updatedAt;
    @Column(name = "profile_url")
    private  String profileUrl ;
    @Column(name = "authenticated", nullable = false)
    private Boolean authenticated ;

    public User() {

    }

    public User( String userName,String name, String email, String password, long phone, String organizationEmail, String createdAt, String updatedAt, String profileUrl, Boolean authenticated) {

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
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
}
