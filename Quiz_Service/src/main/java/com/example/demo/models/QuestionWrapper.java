package com.example.demo.models;

import java.util.Map;

public class QuestionWrapper {
    public int id;
    public String content;
    public Map<String, String> options;

    public QuestionWrapper() {
		super();
	}

	public QuestionWrapper(int id, String content, Map<String, String> options) {
        this.id = id;
        this.content = content;
        this.options = options;
    }

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
