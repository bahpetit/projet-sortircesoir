package m2dl.ivvq.sortircesoir.service;

import m2dl.ivvq.sortircesoir.domain.User;
import m2dl.ivvq.sortircesoir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
       return this.userRepository.save(user);
    }

}
