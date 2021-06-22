package bankmanage.service.impl;

import bankmanage.dao.IAccountDao;
import bankmanage.data.Account;
import bankmanage.service.IAccountService;
import bankmanage.utils.TransActionManager;

import java.util.List;

public class AccountService implements IAccountService {
    private IAccountDao accountDao;
    private TransActionManager transActionManager;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransActionManager(TransActionManager transActionManager) {
        this.transActionManager = transActionManager;
    }

    public List<Account> findAllAccounts() {
        try{
            transActionManager.startTransAction();
            List<Account> res = accountDao.findAllAccount();
            transActionManager.commitTransAction();
            return res;
        }catch (Exception e) {
            transActionManager.rollbackTransAction();
            throw new RuntimeException(e);
        }finally {
            transActionManager.releaseTransAction();
        }
    }

    public Account findAccountById(Integer id) {
        try {
            transActionManager.startTransAction();
            Account account = accountDao.findAccountById(id);
            transActionManager.commitTransAction();
            return account;
        }catch (Exception e) {
            transActionManager.rollbackTransAction();
            throw new RuntimeException(e);
        }finally {
            transActionManager.releaseTransAction();
        }
    }

    public void saveAccount(Account account) {
        try{
            transActionManager.startTransAction();
            accountDao.saveAccount(account);
            transActionManager.commitTransAction();
        }catch (Exception e) {
            transActionManager.rollbackTransAction();
            throw new RuntimeException(e);
        }finally {
            transActionManager.releaseTransAction();
        }
    }

    public void updateAccount(Account account) {
        try{
            transActionManager.startTransAction();
            accountDao.updateAccount(account);
            transActionManager.commitTransAction();
        }catch (Exception e) {
            transActionManager.rollbackTransAction();
            throw new RuntimeException(e);
        }finally {
            transActionManager.releaseTransAction();
        }
    }

    public void deleteAccount(Account account) {
        try{
            transActionManager.startTransAction();
            accountDao.deleteAccount(account);
            transActionManager.commitTransAction();
        }catch (Exception e) {
            transActionManager.rollbackTransAction();
            throw new RuntimeException(e);
        }finally {
            transActionManager.releaseTransAction();
        }
    }

    public void transferMoney(String fromName, String toName, float money) {
        try{
            transActionManager.startTransAction();
            Account fromAccount = accountDao.findAccountByName(fromName);
            Account toAccount = accountDao.findAccountByName(toName);
            fromAccount.setMoney(fromAccount.getMoney()-money);
            toAccount.setMoney(toAccount.getMoney()+money);
            accountDao.updateAccount(fromAccount);
            accountDao.updateAccount(toAccount);
            transActionManager.commitTransAction();
        }catch (Exception e) {
            transActionManager.rollbackTransAction();
            throw new RuntimeException(e);
        }finally {
            transActionManager.releaseTransAction();
        }
    }
}
