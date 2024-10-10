package com.sayaliblog.blogappapis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;
    @Column(name="post_title",length=100,nullable = false)
    private String title;
    @Column(length = 1000000000)
    private String content;
    private String image;
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name="category_id")
   private Category category;
    @ManyToOne
   private User user;

}
