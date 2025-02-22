package com.example.jpa.comment.controller;

import com.example.jpa.comment.dto.request.CommentCreateRequest;
import com.example.jpa.comment.dto.request.CommentUpdateRequest;
import com.example.jpa.comment.dto.response.CommentResponse;
import com.example.jpa.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CommentCreateRequest commentCreateRequest) {
        Long saveResult = commentService.create(commentCreateRequest);
        if (saveResult != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findOne(@PathVariable("id") Long id) {

        CommentResponse commentResponse = commentService.findOne(id);

        return ResponseEntity.ok(commentResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody CommentUpdateRequest commentUpdateRequest) {

        commentService.update(id, commentUpdateRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        commentService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List> findAll() {

        List<CommentResponse> commentResponses = commentService.findAll();

        return ResponseEntity.ok(commentResponses);
    }
}
