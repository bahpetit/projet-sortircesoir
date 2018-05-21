package groovy.m2dl.ivvq.sortircesoir.service

import m2dl.ivvq.sortircesoir.SortirCeSoirApplication
import m2dl.ivvq.sortircesoir.domain.Comment
import m2dl.ivvq.sortircesoir.domain.Place
import m2dl.ivvq.sortircesoir.domain.User
import m2dl.ivvq.sortircesoir.service.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification


import javax.validation.ConstraintViolationException

/**
 * Created by bah on 12/04/2018
 */
@ContextConfiguration
@SpringBootTest(classes = SortirCeSoirApplication.class)
@Transactional
class CommentServiceITest extends Specification {
    @Autowired CommentService commentService

    def "test save a valid comment"() {

        given: "a user"
        User bah = new User(email: "bah@gmail.com", login: "bahpetit", password: "ba@hpetit")

        Place place = new Place(name: "Saint pierre", address: "94 boulevard toulouse")

        and: "a valid comment"
        Comment comment = new Comment(description: "c'est super souhette", user: bah, place: place)

        when: "the comment is saved"
        commentService.saveComment(comment);

        then: "the activite has an id becaus it has been saved"
        comment.id != null

        and: "bah has an id because it has been saved"
        bah.id != null

        and: "place has an id because it has been saved"
        place.id != null

        and: "bah has an comment"
        bah.comments.size() == 1
        bah.comments.first().description == comment.description

        when: "Creating a new activite with Bob as responsable"
        Comment comment1 = new Comment(description: "c'est super souhette", user: bah, place: place)

        and: "the comment is saved"
        commentService.saveComment(comment1);

        then: "the comment has an id becaus it has been saved"
        comment1.id != null

        and: "bah has two activites"
        bah.comments.size() == 2
        bah.comments.last().description == comment1.description
    }

    def "test save a non valid comment"() {
        given: "a non valid comment"
        Comment comment = new Comment()

        when: "the commnent is saved"
        commentService.saveComment(comment);

        then: "A validation exception is thrown"
        thrown ConstraintViolationException

        and: "activite has still null id"
        comment.id == null
    }
}
