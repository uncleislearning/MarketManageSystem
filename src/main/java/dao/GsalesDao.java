package dao;

import entity.Gsales;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xiaomengning on 2017/10/5.
 */
public class GsalesDao {

    private static Connection conn = null;
    private PreparedStatement pst = null;

    public void insertSales(List<Gsales> needUpdate) {
        conn = DBUtils.getConnection();
        String sql = "INSERT INTO GSALES(gnum,gid,sid) VALUES(?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            conn.setAutoCommit(false);

            for( Gsales o: needUpdate){
                pst.setInt(1,o.getGnum());
                pst.setInt(2,o.getGid());
                pst.setInt(3,o.getSid());
                pst.addBatch();
            }
            pst.executeBatch();
            conn.commit();//手动提交
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("sql执行异常:"+e.getMessage());
            try {
                if(!conn.isClosed()){
                    conn.rollback();
                    System.out.println("");
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DBUtils.closeAll(null, pst, conn);
        }

    }
}
