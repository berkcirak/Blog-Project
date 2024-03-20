package com.example.WebSite.service;

import com.example.WebSite.entity.Post;
import com.example.WebSite.entity.User;
import com.example.WebSite.repos.PostRepository;
import com.example.WebSite.request.PostCreateRequest;
import com.example.WebSite.request.PostUpdateRequest;
import com.example.WebSite.dto.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    public PostRepository postRepository;
    public UserService userService;
    public ModelMapper modelMapper;

    public PostService(PostRepository postRepository,UserService userService,ModelMapper modelMapper){
        this.postRepository=postRepository;
        this.userService=userService;
        this.modelMapper=modelMapper;
    }

    public Post addPost(PostCreateRequest postRequest) {
        User theUser=userService.getUser(postRequest.getUserId());
        if (theUser==null){
            return null;
        }
        Post toSave=new Post();
        toSave.setId(postRequest.getId());
        toSave.setTitle(postRequest.getTitle());
        toSave.setText(postRequest.getText());
        toSave.setUser(theUser);
        return postRepository.save(toSave);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(null);
    }

    public Post updatePost(Long postId, PostUpdateRequest updateRequest) {
        Optional<Post> thePost=postRepository.findById(postId);
        if (thePost.isPresent()){
            Post toUpdate=thePost.get();
            toUpdate.setTitle(updateRequest.getTitle());
            toUpdate.setText(updateRequest.getText());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        else{
            throw new RuntimeException("post is not found");
        }
    }

    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<PostDto> getPosts() {
      return postRepository.findAll().stream().map(post -> {
          PostDto postDto=modelMapper.map(post,PostDto.class);
          if (post.getUser() != null){
              postDto.setUsername(post.getUser().getUsername());
          }
          return postDto;
      }).collect(Collectors.toList());
    /*    return postRepository.findAll().stream().map(post ->
        post'un username'i null olarak dönüyor modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    */
    }

}
