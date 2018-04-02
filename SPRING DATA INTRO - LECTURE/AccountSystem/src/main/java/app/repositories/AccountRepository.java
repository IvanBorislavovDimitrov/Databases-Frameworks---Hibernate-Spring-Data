package app.repositories;

import app.models.Account;
import app.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Set<Account> getByUser(User user);
}
