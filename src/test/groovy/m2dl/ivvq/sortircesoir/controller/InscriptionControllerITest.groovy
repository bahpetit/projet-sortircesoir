package groovy.m2dl.ivvq.sortircesoir.controller

import m2dl.ivvq.sortircesoir.SortirCeSoirApplication
import m2dl.ivvq.sortircesoir.controller.InscriptionController
import m2dl.ivvq.sortircesoir.domain.User
import m2dl.ivvq.sortircesoir.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import spock.lang.Specification

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD )
@ContextConfiguration
@SpringBootTest(classes = SortirCeSoirApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class InscriptionControllerITest extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    @Autowired
    private UserRepository userRepository

    @Autowired
    private InscriptionController inscriptionController

    def signUpUrl = "/signup"

    def login = "test"
    def email = "test@test.fr"
    def password = "azerty"

    def "un visiteur s'inscrit sur notre site"() {
        when:
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>()
        map.add("mail", email)
        map.add("login", login)
        map.add("password", password)
        map.add("passwordConfirmation", password)
        def res = restTemplate.postForObject(signUpUrl, map, String.class)

        then:
        for (User u: userRepository.findAll()) {
            System.out.println("user : " + u.getLogin());
        }
        userRepository.findAll().size() == 1

    }
}