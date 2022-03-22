package com.in.akshartrade.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileModel {

    @SerializedName("status")
    @Expose
    Boolean status;


    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    UserProfileData userData;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserProfileData getUserData() {
        return userData;
    }

    public void setUserData(UserProfileData userData) {
        this.userData = userData;
    }

    public class UserProfileData {

        @SerializedName("user_id")
        @Expose
        String user_id;

        @SerializedName("name")
        @Expose
        String name;

        @SerializedName("email")
        @Expose
        String email;

        @SerializedName("mobile_no")
        @Expose
        String mobile_no;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }
    }
}
