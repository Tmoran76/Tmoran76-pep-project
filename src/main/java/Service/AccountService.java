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
//     As a user, I should be able to create a new Account on the endpoint POST localhost:8080/register. The body will contain a representation of a JSON Account, but will not contain an account_id.

// - The registration will be successful if and only if the username is not blank, the password is at least 4 characters long, and an Account with that username does not already exist. If all these conditions are met, the response body should contain a JSON of the Account, including its account_id. The response status should be 200 OK, which is the default. The new account should be persisted to the database.
// - If the registration is not successful, the response status should be 400. (Client error)

    
}
