package com.sayaliblog.blogappapis.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comment {
    @Id
    private String comment_id;
    private String comment;

}
