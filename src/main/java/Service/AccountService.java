package Service;

import java.util.List;

import DAO.AccountDao;
import Model.Account;

public class AccountService {
    AccountDao dao;
    public AccountService(){
        dao = new AccountDao();
    }

    public Account newAccount(Account account){
        List<String> allUsernames = dao.getAllUsernames();
        if(account.getUsername().length() < 1){
            return null;
        }
        else if(account.getPassword().length() < 4){
            return null;
        }
        else if(account != null){
            for(String s: allUsernames){
                if(s == account.getUsername()){
                    return null;
                }
            }
        }
        
        return dao.addAccount(account);
    }
    public Account verAccount(String username, String password){
        return dao.findAccount(username, password);
    }

    
}
