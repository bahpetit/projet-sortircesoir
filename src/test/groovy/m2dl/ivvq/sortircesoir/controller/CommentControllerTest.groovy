package groovy.m2dl.ivvq.sortircesoir.controller

import m2dl.ivvq.sortircesoir.controller.CommentController
import m2dl.ivvq.sortircesoir.service.CommentService
import spock.lang.Specification
/**
 * Created by bah on 18/04/2018.
 */
class CommentControllerTest extends Specification {
    private CommentService commentService
    private CommentController commentController

    void setup() {
        commentService = Mock()
        commentController = new CommentController(commentService: commentService)
    }

    def "test find all comments call to service method"() {

        when: "requesting for all comments of a user on a place"
        commentController.findCommentsOfaUserOnaPlace();

        then: "the request is performed by the comment service"
        1 * commentService.findAllComments()

    }
}
