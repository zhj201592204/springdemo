package bankmanage.dao.impl;

import bankmanage.dao.IAccountDao;
import bankmanage.data.Account;
import bankmanage.utils.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class AccountDao implements IAccountDao {
    private QueryRunner queryRunner;
    private ConnectionUtil connectionUtil;

    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public List<Account> findAllAccount() {
        try{
            return queryRunner.query(connectionUtil.getThreadConnect(),"select * from account",new BeanListHandler<Account>(Account.class));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account findAccountById(Integer id) {
        try{
            return queryRunner.query(connectionUtil.getThreadConnect(),"select * from account where id = ?",new BeanHandler<Account>(Account.class),id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try{
            queryRunner.update(connectionUtil.getThreadConnect(),"insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        try{
            queryRunner.update(connectionUtil.getThreadConnect(),"update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(Account account) {
        try{
            queryRunner.update(connectionUtil.getThreadConnect(),"delete from account where id = ?",account.getId());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Account findAccountByName(String name) {
        try{
             List<Account> accounts = queryRunner.query(connectionUtil.getThreadConnect(),"select * from account where name = ?",new BeanListHandler<Account>(Account.class),name);
            if(accounts == null || accounts.size() == 0) {
                return null;
            }else if(accounts.size() > 1) {
                throw new RuntimeException("查询账户不唯一");
            }else {
                return accounts.get(0);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
