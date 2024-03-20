package com.example.WebSite.controller;

import com.example.WebSite.entity.Post;
import com.example.WebSite.request.PostCreateRequest;
import com.example.WebSite.request.PostUpdateRequest;
import com.example.WebSite.dto.PostDto;
import com.example.WebSite.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    public ModelMapper modelMapper;
    public PostService postService;

    public PostController(PostService postService,ModelMapper modelMapper){
        this.postService=postService;
        this.modelMapper=modelMapper;
    }
    @PostMapping("/add")
    public Post addPost(@RequestBody PostCreateRequest postRequest){
        return postService.addPost(postRequest);
    }
    @GetMapping("/list")
    public List<PostDto> getPosts(){
        return postService.getPosts();
    }


    @GetMapping("/getPost/{postId}")
    public Post getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    @PutMapping("/update/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updateRequest){
        return postService.updatePost(postId,updateRequest);
    }
    @DeleteMapping("/delete/{postId}")
    public void deleteById(@PathVariable Long postId){
        postService.deletePostById(postId);
    }

}
