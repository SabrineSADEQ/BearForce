package initializer;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import fr.isika.cda.javaee.dao.accounts.AccountDao;
import fr.isika.cda.javaee.entity.accounts.Account;
import fr.isika.cda.javaee.entity.accounts.Role;
import fr.isika.cda.javaee.utils.PasswordUtils;
import fr.isika.cda.javaee.viewModel.AccountViewModel;

@Singleton
@Startup
public class DataInitializer {

    @Inject
    private AccountDao accountDao;

    @PostConstruct
    public void initialize() {
        createDefaultUser();
    }

    private void createDefaultUser() {
        // Check if the default user already exists
       
            AccountViewModel defaultUser = new AccountViewModel();
            defaultUser.setEmail("azerty");
            defaultUser.setPassword("azerty");
            defaultUser.setRole(Role.SUPER_ADMIN);

            accountDao.createAccount(defaultUser);
        
    }
}