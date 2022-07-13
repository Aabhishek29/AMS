package com.zeus4th.ams.model;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    @Column(name = "user_name")
    private  String user_name;

    @Column(name = "user_email")
    private  String user_email = null;
    @Column(name = "user_password")
    private  String user_password = null;
    @Column(name = "user_ph_no")
    private  long user_ph_no = 0;
    @Column(name = "user_organization_email")
    private  String user_organization_email = null;
    @Column(name = "created_at")
    private  String created_at = null;
    @Column(name = "updated_at")
    private  String updated_at= null;
    @Column(name = "profile_url")
    private  String profile_url = null;

    @Column(name = "is_authenticated")
    private Boolean is_authenticated =false;



    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_ph_no(long user_ph_no) {
        this.user_ph_no = user_ph_no;
    }

    public void setUser_organization_email(String user_organization_email) {
        this.user_organization_email = user_organization_email;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public void setIs_authenticated(Boolean is_authenticated) {
        this.is_authenticated = is_authenticated;
    }




    public User(long user_id, String user_name, String user_email, String user_password, long user_ph_no, String user_organization_email, String created_at, String updated_at, String profile_url){
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password  = user_password;
        this.user_ph_no = user_ph_no;
        this.user_organization_email = user_organization_email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.profile_url = profile_url;


    }
    public User(long user_id, String user_name){
        this.user_id = user_id;
        this.user_name = user_name;
    }

    public User(long user_id, String user_name, Boolean is_authenticated){
        this.user_id = user_id;
        this.user_name = user_name;
        this.is_authenticated = is_authenticated;
    }

    public User() {

    }


    public long getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public long getUser_ph_no() {
        return user_ph_no;
    }

    public String getUser_organization_email() {
        return user_organization_email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public Boolean getIs_authenticated() {

        return is_authenticated;
    }
}
