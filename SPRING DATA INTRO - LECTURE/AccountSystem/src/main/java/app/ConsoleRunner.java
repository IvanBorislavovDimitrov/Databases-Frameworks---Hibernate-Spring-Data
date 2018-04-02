package app;

import app.models.Account;
import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import app.services.AccountServiceImp;
import app.services.UserServiceImp;

import java.math.BigDecimal;

@Component
@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    private UserServiceImp userServiceImp;
    private AccountServiceImp accountServiceImp;

    @Autowired
    public ConsoleRunner(UserServiceImp userServiceImp, AccountServiceImp accountServiceImp) {
        this.userServiceImp = userServiceImp;
        this.accountServiceImp = accountServiceImp;
    }

    @Override
    public void run(String... strings) {
        User user = new User();
        user.setUsername("Petko");
        user.setAge(133);

        Account account1 = new Account();
        account1.setBalance(new BigDecimal(2000));
        account1.setUser(user);

        user.getAccounts().add(account1);

        this.userServiceImp.registerUser(user);
        this.accountServiceImp.withdrawMoney(new BigDecimal(200), account1.getId());
        this.accountServiceImp.transferMoney(new BigDecimal(200), 1L);
    }
}
