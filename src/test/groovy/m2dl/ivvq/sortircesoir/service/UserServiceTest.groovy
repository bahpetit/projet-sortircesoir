package groovy.m2dl.ivvq.sortircesoir.service

import m2dl.ivvq.sortircesoir.domain.User
import m2dl.ivvq.sortircesoir.repository.UserRepository
import m2dl.ivvq.sortircesoir.service.UserService
import org.springframework.data.repository.PagingAndSortingRepository
import spock.lang.Specification


class UserServiceTest extends Specification {
    UserService userService
    UserRepository userRepository

    void setup() {
        userRepository = Mock()
        userService = new UserService()
        userService.userRepository = userRepository
    }

    def "check type of userRepository"() {
        expect: "userRepository is a Spring repository"
        userRepository instanceof PagingAndSortingRepository
    }

    def "test delegation of save of an user to the repository"() {
        given: "an user"
        def user = Mock(User)

        when: "the user is saved"
        userService.saveUser(user);

        then: "the save is delegated to the userRepository"
        1 * userRepository.save(user)
    }

}
