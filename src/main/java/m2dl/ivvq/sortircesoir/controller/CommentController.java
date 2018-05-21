package m2dl.ivvq.sortircesoir.controller;

import m2dl.ivvq.sortircesoir.domain.Comment;
import m2dl.ivvq.sortircesoir.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sortircesoir")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/new")
    public Comment saveComment(@RequestBody Comment comment) {
        return  commentService.saveComment(comment);
    }

    @RequestMapping("/commentsOfaUserOnaPlace")
    public Iterable<Comment> findCommentsOfaUserOnaPlace() {
        return commentService.findAllComments();
    }


    @RequestMapping(value = "comments/{comment_id}", method = RequestMethod.DELETE)
    public void removePartnership(@PathVariable("comment_id")Long commentId) {
        Comment comment = commentService.findCommentById(commentId);
        commentService.removeComment(comment);
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
        Comment commentUpdate = commentService.saveComment(comment);
        return  new ResponseEntity<Comment>(commentUpdate, HttpStatus.OK);
    }
}
