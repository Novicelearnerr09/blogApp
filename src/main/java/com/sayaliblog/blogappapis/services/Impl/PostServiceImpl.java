package com.sayaliblog.blogappapis.services.Impl;

import com.sayaliblog.blogappapis.entities.Category;
import com.sayaliblog.blogappapis.entities.Post;
import com.sayaliblog.blogappapis.entities.User;
import com.sayaliblog.blogappapis.exceptions.ResourceNotFoundException;
import com.sayaliblog.blogappapis.payloads.CategoryDto;
import com.sayaliblog.blogappapis.payloads.PostDto;
import com.sayaliblog.blogappapis.payloads.PostResponse;
import com.sayaliblog.blogappapis.repositories.CategoryRepo;
import com.sayaliblog.blogappapis.repositories.PostRepo;
import com.sayaliblog.blogappapis.repositories.UserRepo;
import com.sayaliblog.blogappapis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo postrepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        Post post =this.modelMapper.map(postDto, Post.class);
        post.setImage("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post addPost=this.postrepo.save(post);
        //this.modelMapper.map(addPost, PostDto.class);
        return this.modelMapper.map(addPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postid) {
        Post post=this.postrepo.getOne(postid);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        this.postrepo.save(post);
        PostDto postDto1=this.modelMapper.map(post, PostDto.class);
        return postDto1;
    }

    @Override
    public void deletePost(Integer postid) {
        Post post1 =this.postrepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post","postid",postid));
        this.postrepo.delete(post1);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryid) {

        Category cat = this .categoryRepo.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryid));
        List<Post> posts = this.postrepo.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postid) {
        Post post1 = this.postrepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post","postid",postid));
        return this.modelMapper.map(post1, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy ) {

//        int pageSize = 5;
//        int pageNumber = 2;

        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        Page<Post> pagePost = this.postrepo.findAll(p);

        List<Post> allPosts = pagePost.getContent();

       // List<Post> allpost = this.postrepo.findAll();
        List<PostDto> postDtos= allPosts.stream().map((cate)->this.modelMapper.map(cate, PostDto.class)).collect(Collectors.toList());
       // List<CategoryDto> categoryDtos= category.stream().map((cate)->this.modelMapper.map(cate,CategoryDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getNumberOfElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User u = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        List<Post> posts = this.postrepo.findByUser(u);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

       List<Post> posts= this.postrepo.serachByTitle("%"+keyword+"%");
        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
