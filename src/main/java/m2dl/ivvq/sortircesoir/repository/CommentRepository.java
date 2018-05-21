package m2dl.ivvq.sortircesoir.repository;

import m2dl.ivvq.sortircesoir.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    @Query("select comment from Comment comment join fetch comment.user order by comment.description asc")
    List<Comment> findAll();
}
