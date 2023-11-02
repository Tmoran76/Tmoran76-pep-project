package DAO;



public class AccountDao {
    private static AccountDao accountDao = null;
    private AccountDao(){

    };
    static AccountDao accountInstance(){
        if(accountDao == null){
            accountDao = new AccountDao();
        }
        return accountDao;
    }
    
}
