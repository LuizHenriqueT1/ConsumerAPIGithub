package com.consumer.domain;

import java.io.Serializable;

public class ConsumerRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    private float id;
    private String name;
    private String html_url;

    public ConsumerRepository() {
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
