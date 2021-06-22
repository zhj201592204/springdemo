package bankmanage.utils;

import java.sql.SQLException;

public class TransActionManager {

    private ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public void startTransAction() {
        try{
            //关闭自动提交事务
            connectionUtil.getThreadConnect().setAutoCommit(false);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commitTransAction() {
        try {
            connectionUtil.getThreadConnect().commit();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackTransAction() {
        try {
            connectionUtil.getThreadConnect().rollback();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void releaseTransAction() {
        try {
            connectionUtil.getThreadConnect().close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
