package groovy.m2dl.ivvq.sortircesoir.service

import m2dl.ivvq.sortircesoir.SortirCeSoirApplication
import m2dl.ivvq.sortircesoir.domain.User
import m2dl.ivvq.sortircesoir.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.validation.ConstraintViolationException

/**
 * Created by bah on 12/04/2018
 */
@ContextConfiguration
@SpringBootTest(classes = SortirCeSoirApplication.class)
class UserServiceITest extends Specification {

    @Autowired
    UserService userService

    def "test save a valid user"() {
        given: "a valid user"
        User bah = new User("bah@gmail", "bahpetit", "pôdemon")

        when: "the user is saved"
        userService.saveUser(bah);

        then: "the user has an id"
        bah.id != null

    }

    def "test save a non valid utilisateur"() {
        given: "a non valid user"
        User bah = new User("", "bahpetit", "pôdemon")

        when: "the user is saved"
        userService.saveUser(bah)

        then: "A validation exception is thrown"
        thrown ConstraintViolationException

        and: "bah has still null id"
        bah.id == null
    }

}