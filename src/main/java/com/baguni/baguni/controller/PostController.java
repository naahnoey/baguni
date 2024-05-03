package com.baguni.baguni.controller;

import com.baguni.baguni.domain.post.Post;
import com.baguni.baguni.domain.post.PostRepository;
import com.baguni.baguni.payload.response.MessageResponse;
import com.baguni.baguni.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    AuthController authController;

    // 게시글 불러오기
    // 전체 게시글
    @GetMapping("/list")
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(required = false) String title) {
        try {
            List<Post> posts;

            if (title == null) {
                posts = new ArrayList<>(postRepository.findAll());
            } else {
                posts = new ArrayList<>(postRepository.findByTitleContaining(title));
            }

            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 특정 게시글
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Optional<Post> postData = postRepository.findById(id);

        if (postData.isPresent()) {
            return new ResponseEntity<>(postData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 유저 정보로 찾기
    @GetMapping("/{nickname}")
    public ResponseEntity<List<Post>> findByNickname(@PathVariable("nickname") String nickname) {
        try {
            List<Post> posts = postRepository.findByUserNickname(nickname);

            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 게시글 작성하기
    @PostMapping("/")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
            UserDetailsImpl userInfo = authController.getCurrentUserInfo();

            if (userInfo == null) {
                return new ResponseEntity<>(new MessageResponse("로그인 필요"), HttpStatus.NOT_ACCEPTABLE);
            }

            System.out.println(userInfo.getId());
            Post _post = postRepository.save(new Post(post.getTitle(), post.getContent(), userInfo.getId(), userInfo.getNickname()));
            return new ResponseEntity<>(_post, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new MessageResponse("서버 오류 발생"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        Optional<Post> postData = postRepository.findById(id);

        if (postData.isPresent()) {
            Post _post = postData.get();
            _post.setTitle(post.getTitle());
            _post.setContent(post.getContent());
            return new ResponseEntity<>(postRepository.save(_post), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") Long id) {
        try {
            postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
