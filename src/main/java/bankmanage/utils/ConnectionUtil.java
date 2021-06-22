package bankmanage.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtil {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getThreadConnect() throws SQLException {
        /**
         * 从ThreadLocal获取
         */
        Connection coon = threadLocal.get();
        if(coon == null) {
            coon = dataSource.getConnection();
            threadLocal.set(coon);
        }
        return coon;
    }

    /**
     * 解绑线程和连接
     * 无论是线程池还是连接池，调用close方法并不是关闭，而是将取出来的线程或连接还回池中
     * 而并非关闭连接或线程
     * 因此在下次使用时，我们获取的时候还能获取到，但是它却不能用了（因为被close了）
     * 所以完成一次线程操作后需要解绑
     * 这也是WEB开发中需要注意的问题
     */
    public void removeConnection() {
        threadLocal.remove();
    }
}
