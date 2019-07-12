package com.crepo.taskmaster.wk6.taskMaster;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.UUID;

@DynamoDBTable(tableName = "TaskMaster")
public class TheTaskMaster {

    private UUID id;
    private String title;
    private String description;
    private String assignee;
    private String status;
    private String picture;
    private String picReSizer;

    public TheTaskMaster(){}

    public TheTaskMaster(String picture){

        this.picture = picture;

    }


    public TheTaskMaster(String title, String description, String picture, String picReSizer){

        this.title = title;
        this.description = description;
        this.status = "Available";
        this.picture = picture;
        this.picReSizer = picReSizer;


    }

    public TheTaskMaster(String title, String description, String assignee, String picture, String picReSizer){

        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.status = "Assigned";
        this.picture = picture;
        this.picReSizer = picReSizer;

    }
    //GETTERS & SETTERS
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DynamoDBAttribute
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @DynamoDBAttribute
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @DynamoDBAttribute
    public String getPicReSizer() {
        return picReSizer;
    }

    public void setPicReSizer(String picReSizer) {
        this.picReSizer = picReSizer;
    }
}
