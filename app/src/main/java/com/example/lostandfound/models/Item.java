package com.example.lostandfound.models;

import java.io.Serializable;

public class Item implements Serializable {
    private String id;
    private String title;
    private String description;
    private String category;       // e.g. "Electronics", "Bags", "Documents"
    private String type;           // "lost" or "found"
    private String location;
    private String contactName;
    private String contactPhone;
    private String imagePath;      // local file path
    private long timestamp;
    private String postedByUserId;
    private boolean resolved;

    public Item() {}

    public Item(String id, String title, String description, String category,
                String type, String location, String contactName,
                String contactPhone, String imagePath, long timestamp,
                String postedByUserId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.type = type;
        this.location = location;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.imagePath = imagePath;
        this.timestamp = timestamp;
        this.postedByUserId = postedByUserId;
        this.resolved = false;
    }

    // --- Getters and Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public String getPostedByUserId() { return postedByUserId; }
    public void setPostedByUserId(String postedByUserId) { this.postedByUserId = postedByUserId; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
}