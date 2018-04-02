package app.services;

import app.models.Account;
import app.models.User;

import java.math.BigDecimal;
import java.util.Set;

public interface AccountService {

    void withdrawMoney(BigDecimal money, Long id);
    void transferMoney(BigDecimal money, Long id);
    Set<Account> getByUser(User user);

}
