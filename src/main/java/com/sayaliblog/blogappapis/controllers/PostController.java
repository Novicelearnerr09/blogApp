package com.sayaliblog.blogappapis.controllers;

import com.sayaliblog.blogappapis.entities.Post;
import com.sayaliblog.blogappapis.payloads.ApiResponse;
import com.sayaliblog.blogappapis.payloads.PostDto;
import com.sayaliblog.blogappapis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Post")
public class PostController {
    @Autowired
    private PostService postService;
    //createg
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@PathVariable int userId, @PathVariable int categoryId, @RequestBody PostDto postDto) {
        PostDto createPost= postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    //Get by user
    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
    {
       List<PostDto> posts = this.postService.getPostsByUser(userId);

       return new ResponseEntity<List<PostDto>>(posts, HttpStatus.CREATED);
    }


    //get by category
    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){

       List<PostDto> posts =  this.postService.getPostsByCategory(categoryId);
       return new ResponseEntity<List<PostDto>>(posts, HttpStatus.CREATED);
    }

    //get post by id
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostId(@PathVariable Integer postId)
    {
        PostDto postDto =this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }
    //get all post
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPost()
    {
       List<PostDto> postsDto = this.postService.getAllPosts();

       return new ResponseEntity<List<PostDto>>(postsDto, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> Deletepost(@PathVariable Integer id)
    {

        this.postService.deletePost(id);
       return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    //update
    @PutMapping("/{id}")
    ResponseEntity<PostDto> updatePost(@PathVariable Integer id, @RequestBody PostDto postDto)
    {
        PostDto postDto1 = this.postService.updatePost(postDto,id);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.OK);

    }

    //search..........................
    @GetMapping("/search/{title}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String title)
    {
       List<PostDto> post = this.postService.searchPosts(title);
       return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);
    }


}
