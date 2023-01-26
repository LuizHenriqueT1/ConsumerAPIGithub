package com.consumer.domain;

import java.io.Serializable;

public class ConsumerRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    private float id;
    private String node_id;
    private String name;
    private String full_name;

    private boolean _private;
    private ConsumerUser OwnerObject;
    private String html_url;
    private String description = null;
    private boolean fork;
    private String url;


    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public boolean is_private() {
        return _private;
    }

    public void set_private(boolean _private) {
        this._private = _private;
    }

    public ConsumerUser getOwnerObject() {
        return OwnerObject;
    }

    public void setOwnerObject(ConsumerUser ownerObject) {
        OwnerObject = ownerObject;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
