package bankmanage.service;

import bankmanage.data.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 查询所有账户信息
     * @return 账户信息List
     */
    List<Account> findAllAccounts();

    /**
     * 根据账户id查找账户信息
     * @param id 账户id
     * @return 账户bean
     */
    Account findAccountById(Integer id);

    /**
     * 保存账户信息
     * @param account 账户bean
     */
    void saveAccount(Account account);

    /**
     * 更新账户信息
     * @param account 账户bean
     */
    void updateAccount(Account account);

    /**
     * 删除账户信息
     * @param account 账户bean
     */
    void deleteAccount(Account account);

    /**
     * 根据用户名称查找用户信息
     * @param fromName 转账源账户
     * @param toName 转账目标账户
     * @param money 转账金额
     */
    void transferMoney(String fromName,String toName,float money);
}
