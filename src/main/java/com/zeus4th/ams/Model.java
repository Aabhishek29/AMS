package com.zeus4th.ams;

public class Model {

    private final long user_id;
    private final String user_name;
    private final String user_email;
    private final String user_password;
    private final long user_ph_no;
    private final String user_organization_email;
    private final String created_at;
    private final String updated_at;
    private final String profile_url;


    public Model(long user_id, String user_name, String user_email, String user_password, long user_ph_no, String user_organization_email, String created_at, String updated_at, String profile_url){
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
}
