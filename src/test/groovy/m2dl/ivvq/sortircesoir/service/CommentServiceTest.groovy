package groovy.m2dl.ivvq.sortircesoir.service

import m2dl.ivvq.sortircesoir.domain.Comment
import m2dl.ivvq.sortircesoir.domain.Place
import m2dl.ivvq.sortircesoir.domain.User
import m2dl.ivvq.sortircesoir.repository.CommentRepository
import m2dl.ivvq.sortircesoir.service.CommentService
import org.springframework.data.repository.PagingAndSortingRepository
import spock.lang.Specification

class CommentServiceTest extends Specification {
    CommentService commentService
    CommentRepository commentRepository

    void setup() {
        commentRepository = Mock()
        commentService = new CommentService()
        commentService.commentRepository = commentRepository
    }

    def "check type of commentRepository"() {
        expect: "commentRepository is a Spring repository"
        commentRepository instanceof PagingAndSortingRepository
    }

    def "test delegation of save of an Comment to the repository"() {
        given: "an comment"
        def comment = Mock(Comment) {
            getUser() >> Mock(User) {
                getComments() >> []
            }
            getPlace()>>Mock(Place){
                getComments() >> []
            }
        }

        when: "the comment is saved"
        commentService.saveComment(comment);

        then: "the save is delegated to the commentRepository"
        1 * commentRepository.save(comment)
    }

    def "test delegation of finding all comments to the repository"() {
        when: "requesting for all comments"
        commentService.findAllComments()

        then: "the request is delegated to the commentRepository"
        1 * commentRepository.findAll()
    }

}
