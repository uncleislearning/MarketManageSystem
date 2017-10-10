package dao;

import entity.SalesMan;
import org.apache.commons.lang3.StringUtils;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaomengning on 2017/10/5.
 */
public class SalesManDao {
    private Connection conn=null;
    private PreparedStatement pst=null;
    private ResultSet rs = null;






    private List<SalesMan> query(String sql,String[] paras) {
        List res = new ArrayList();
        conn = DBUtils.getConnection();

        try {
                pst = conn.prepareStatement(sql);
                if(null != paras) {
                    for (int i = 0; i < paras.length; i++) {
                        pst.setString(i + 1, paras[i]);
                    }
                }
                rs = pst.executeQuery();
                if (null != rs) {
                    while (rs.next()) {
                        SalesMan s = new SalesMan();
                        s.setSid(rs.getInt(1));
                        s.setSname(rs.getString(2));
                        s.setSpwd(rs.getString(3));
                        res.add(s);
                    }
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    /**
     * 查询saleman表中所有的账户信息
     *
     * @return
     */
    public List<SalesMan> query() {
        String sql = "SELECT * FROM salesman";
        return query(sql,null);
//        List res = new ArrayList();
//        conn = DBUtils.getConnection();
//        String sql = "SELECT * FROM salesman";
//        try {
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//            if (null != rs) {
//                while (rs.next()) {
//                    SalesMan s = new SalesMan();
//                    s.setSid(rs.getInt(1));
//                    s.setSname(rs.getString(2));
//                    s.setSpwd(rs.getString(3));
//                    res.add(s);
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return res;
    }

    /**
     * 根据用户名和密码查询营业员账户信息
     *
     * @return
     */
    public List<SalesMan> query(SalesMan salesMan) {
        String sql = "SELECT sid,sname,spwd FROM salesman WHERE sname = ? and spwd = ?";
        return query(sql,new String[]{salesMan.getSname(),salesMan.getSpwd()});
//        List res = new ArrayList();
//        conn = DBUtils.getConnection();
//        String sql = "SELECT sid,sname,spwd FROM salesman WHERE sname = ? and spwd = ?";
//        try {
//            pst = conn.prepareStatement(sql);
//            pst.setString(1,salesMan.getSname());
//            pst.setString(2,salesMan.getSpwd());
//            rs = pst.executeQuery();
//            if (null != rs) {
//                while (rs.next()) {
//                    SalesMan s = new SalesMan();
//                    s.setSid(rs.getInt(1));
//                    s.setSname(rs.getString(2));
//                    s.setSpwd(rs.getString(3));
//                    res.add(s);
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return res;
    }


    public List<SalesMan> query(String sname) {
        String sql = "SELECT sid,sname,spwd FROM salesman WHERE sname = ?";
        return query(sql,new String[]{sname});
//        List res = new ArrayList();
//        conn = DBUtils.getConnection();
//        String sql = "SELECT sid,sname,spwd FROM salesman WHERE sname = ?";
//        try {
//            pst = conn.prepareStatement(sql);
//            pst.setString(1,sname);
//            rs = pst.executeQuery();
//            if (null != rs) {
//                while (rs.next()) {
//                    SalesMan s = new SalesMan();
//                    s.setSid(rs.getInt(1));
//                    s.setSname(rs.getString(2));
//                    s.setSpwd(rs.getString(3));
//                    res.add(s);
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return res;
    }

    /**
     * 添加营业员账户
     * @param salesMan
     * @return
     */
    public boolean addSaleMan(SalesMan salesMan) {
        conn = DBUtils.getConnection();
        String sql = "INSERT INTO salesman(sname,spwd) VALUES(?,?)";
        int res = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, salesMan.getSname());
            pst.setString(2,salesMan.getSpwd());
            res = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql执行异常:"+e.getMessage());
        } finally {
            DBUtils.closeAll(null, pst, conn);
        }

        if (res == 0) { //执行添加操作失败
            return false;
        } else {
            return true;
        }
    }



    public boolean updateSalesMan(int n, SalesMan update) {
        conn = DBUtils.getConnection();
        int res = 0;

        try {
            if(n==1){
               String sql = "UPDATE salesman SET sname = ? WHERE sid = ?";
               pst = conn.prepareStatement(sql);
               pst.setString(1,update.getSname());
            }else if(n==2){
               String sql = "UPDATE salesman SET spwd = ? WHERE sid = ?";
               pst = conn.prepareStatement(sql);
               pst.setString(1,update.getSpwd());
            }
            pst.setInt(2, update.getSid());
            res = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(null,pst,conn);
        }

        if (res == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteSalesMan(int sid) {
        conn = DBUtils.getConnection();
        String sql = "DELETE FROM salesman WHERE sid = ?";
        int res = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,sid);
            res = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(null,pst,conn);
        }

        if(res == 0){
            return false;
        }else {
            return true;
        }
    }
}
