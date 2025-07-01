package com.example.demo.models;

import java.util.List;

import java.util.Map;

public class QuestionWrapper {
    private int id;
    private String content;
    private Map<String, String> options;  // Same as in Question model

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
