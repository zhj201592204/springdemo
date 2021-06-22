package bankmanage.dao;

import bankmanage.data.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有的账户信息
     * @return 账户信息List
     */
    List<Account> findAllAccount();

    /**
     * 根据账户id查找账户信息
     * @param id 账户id
     * @return 账户信息bean
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
     * 根据账户名称查找账户信息
     * @param name 账户名称
     * @return 账户信息bean
     */
    Account findAccountByName(String name);
}
