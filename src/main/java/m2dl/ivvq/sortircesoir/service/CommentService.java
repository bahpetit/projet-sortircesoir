package m2dl.ivvq.sortircesoir.service;

import m2dl.ivvq.sortircesoir.domain.Comment;
import m2dl.ivvq.sortircesoir.domain.Place;
import m2dl.ivvq.sortircesoir.domain.User;
import m2dl.ivvq.sortircesoir.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        if(comment == null){
            throw new IllegalArgumentException("Comment must not be null");
        }
         Comment commentSaved = this.commentRepository.save(comment);
         User user = comment.getUser();
         Place place = comment.getPlace();
         user.getComments().add(comment);
         place.getComments().add(comment);
        return commentSaved;
    }

    public Iterable<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public void removeComment(Comment comment) {
        this.commentRepository.delete(comment);
    }

    public Comment findCommentById(Long commentId) {
        return  this.commentRepository.findOne(commentId);
    }

}
