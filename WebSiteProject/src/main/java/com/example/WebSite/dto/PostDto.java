package com.example.WebSite.dto;

import com.example.WebSite.entity.Post;

public class PostDto {

    Long id;
    String title;
    String text;
    String username;
    public PostDto(){

    }

    public PostDto(Post entity){
        this.id= entity.getId();
        this.username=entity.getUser().getUsername();
        this.text=entity.getText();
        this.title=entity.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
