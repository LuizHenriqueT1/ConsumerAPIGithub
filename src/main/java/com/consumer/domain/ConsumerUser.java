package com.consumer.domain;

import java.io.Serializable;

public class ConsumerUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private float id;
	private String login;
	private String html_url;
	private float public_repos;
	private String created_at;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public float getId() {
		return id;
	}

	public void setId(float id) {
		this.id = id;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public float getPublic_repos() {
		return public_repos;
	}

	public void setPublic_repos(float public_repos) {
		this.public_repos = public_repos;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
