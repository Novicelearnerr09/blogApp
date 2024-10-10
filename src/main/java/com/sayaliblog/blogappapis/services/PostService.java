package com.sayaliblog.blogappapis.services;

import com.sayaliblog.blogappapis.entities.Post;
import com.sayaliblog.blogappapis.payloads.PostDto;

import java.util.List;

public interface PostService {
    //create post
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postid);
    void deletePost(Integer postid);
    List<PostDto> getPostsByCategory(Integer categoryid);
    PostDto getPostById(Integer postid);
    List<PostDto> getAllPosts();
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);

}
