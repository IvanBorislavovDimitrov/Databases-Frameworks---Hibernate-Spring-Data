package app.services;

import app.models.Account;
import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import app.repositories.AccountRepository;

import java.math.BigDecimal;
import java.util.Set;

@Service
@Primary
public class AccountServiceImp implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account account = this.accountRepository.findOne(id);
        if (account == null) {
            throw new IllegalArgumentException("This account is not contained!");
        }
        if (account.getBalance().subtract(money).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Not enough money!");
        }

        account.setBalance(account.getBalance().subtract(money));
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account account = this.accountRepository.findOne(id);
        if (account == null) {
            throw new IllegalArgumentException("This account is not contained!");
        }
        account.setBalance(account.getBalance().add(money));
    }

    @Override
    public Set<Account> getByUser(User user) {
        return this.accountRepository.getByUser(user);
    }
}
