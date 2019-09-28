package com.exemple.jersey.filter;

import com.exemple.jersey.model.UserSex;

import javax.ws.rs.QueryParam;

public class UserFilterBean {
    private @QueryParam("minAge")
    long minAge;
    private @QueryParam("minWeight")
    int minWeight;
    private @QueryParam("sex")
    UserSex sex;

    public long getMinAge() {
        return minAge;
    }

    public void setMinAge(long minAge) {
        this.minAge = minAge;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(int minWeight) {
        this.minWeight = minWeight;
    }

    public UserSex getSex() {
        return sex;
    }

    public void setSex(UserSex sex) {
        this.sex = sex;
    }
}