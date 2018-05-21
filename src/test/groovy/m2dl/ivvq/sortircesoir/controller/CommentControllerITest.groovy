package groovy.m2dl.ivvq.sortircesoir.controller

import m2dl.ivvq.sortircesoir.SortirCeSoirApplication
import m2dl.ivvq.sortircesoir.domain.Comment
import m2dl.ivvq.sortircesoir.domain.Place
import m2dl.ivvq.sortircesoir.domain.User
import m2dl.ivvq.sortircesoir.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(classes = SortirCeSoirApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentControllerITest extends Specification {
    @Autowired
    private TestRestTemplate restTemplate

    @Autowired
    private CommentService commentService

    def "test save a valid comment"() {

        given: "a user"
        User bah = new User(email: "bah@gmail.com", login: "bahpetit", password: "ba@hpetit")

        Place place = new Place(name: "Saint pierre", address: "94 boulevard toulouse")

        and: "a valid comment"
        Comment comment = new Comment(description: "c'est super souhette", user: bah, place: place)

        when: "post the comment"
        String status = this.restTemplate.postForEntity("/api/v1/sortircesoir/new", comment, String.class)

        then: "the result is equal to 200"
        status.contains("200")
    }

    def "test save an invalid comment"() {
        given: "a user"
        User bah = new User(email: "bah@gmail.com", login: "bahpetit", password: "ba@hpetit")

        Place place = new Place(name: "Saint pierre", address: "94 boulevard toulouse")

        and: "a invalid comment"
        Comment comment = new Comment(description: "", user: bah, place: place)

        when: "post the invalid comment"
        String status = this.restTemplate.postForEntity("/api/v1/sortircesoir/new", comment, String.class)

        then: "the result is equal to 500"
        status.contains("500")
    }

    def "test delete a comment"() {
        given: "a user"
        User bah = new User(email: "bah@gmail.com", login: "bahpetit", password: "ba@hpetit")

        Place place = new Place(name: "Saint pierre", address: "94 boulevard toulouse")

        and: "a valid comment"
        Comment comment = new Comment(description: "c'est super souhette", user: bah, place: place)

        when: "update a comment"
        Comment commentSaved = this.commentService.saveComment(comment)

        and: "deleting the saved comment"
        this.restTemplate.delete("/api/v1/sortircesoir/comments/"+commentSaved.getId())
        and: "search by id"
        Comment commentRemoved = this.commentService.findCommentById(commentSaved.getId())
        then: "the commentRemoved is null"
        commentRemoved == null
    }

    def "Test the modification of a comment"() {

        given: "a user"
        User bah = new User(email: "bah@gmail.com", login: "bahpetit", password: "ba@hpetit")

        Place place = new Place(name: "Saint pierre", address: "94 boulevard toulouse")

        and: "a valid comment"
        Comment comment = new Comment(description: "c'est super souhette", user: bah, place: place)
        String description = comment.getDescription()

        and: "a comment saved"
        Comment commentSaved = this.commentService.saveComment(comment)

        and : "update description"

        commentSaved.setDescription("c'est hyper sympha pour les vrai")

        when: "put the comment"
        HttpEntity<Comment> entity = new HttpEntity<Comment>(commentSaved);
        String status = restTemplate.exchange("/api/v1/sortircesoir/update", HttpMethod.PUT, entity, Comment.class);
        Comment commentUpdate = this.commentService.findCommentById(commentSaved.getId())

        then: "the result is equal to 200"
        status.contains("200")

        and:"same id"
        commentUpdate.getId() == commentSaved.getId()

        and:"different description"
        commentUpdate.getDescription() != description

    }


}
