package m2dl.ivvq.sortircesoir.repository;

import m2dl.ivvq.sortircesoir.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findByLoginAndPassword(String login, String password);
    List<User> findUserByLogin(String login);
}
