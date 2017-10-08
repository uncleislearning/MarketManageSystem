package dao;

import entity.Goods;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaomengning on 2017/10/2.
 */
public class GoodsDao {
    private static Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;


    public boolean addGoods(String gname, float gprice, int gcount) {
        conn = DBUtils.getConnection();
        String sql = "INSERT INTO GOODS(GNAME,GPRICE,GNUM) VALUES(?,?,?)";
        int res = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, gname);
            pst.setFloat(2, gprice);
            pst.setInt(3, gcount);
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

    public List<Goods> queryGoods() {
        List res = new ArrayList();
        conn = DBUtils.getConnection();
        String sql = "SELECT gname,gprice,gnum FROM GOODS";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (null != rs) {
                while (rs.next()) {
                    Goods g = new Goods();
                    g.setGname(rs.getString(1));
                    g.setGprice(rs.getFloat(2));
                    g.setGnum(rs.getInt(3));
                    res.add(g);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    public List<Goods> queryGoods(String gname) {
        List res = new ArrayList();
        conn = DBUtils.getConnection();
        String sql = "SELECT gname,gprice,gnum FROM GOODS WHERE gname=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, gname);
            rs = pst.executeQuery();

            if (null != rs) {

                while (rs.next()) {
                    Goods g = new Goods();
                    g.setGname(rs.getString(1));
                    g.setGprice(rs.getFloat(2));
                    g.setGnum(rs.getInt(3));
                    res.add(g);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean updateGoods(Object update, String gname) {
        conn = DBUtils.getConnection();


        int res = 0;
        try {
            if (update instanceof String) {
                String sql = "UPDATE GOODS SET gname = ? WHERE gname = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, (String) update);
            } else if (update instanceof Float) {
                String sql = "UPDATE GOODS SET gprice = ? WHERE gname = ?";
                pst = conn.prepareStatement(sql);
                pst.setFloat(1, (Float) update);
            } else if (update instanceof Integer) {
                String sql = "UPDATE GOODS SET gnum = ? WHERE gname = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, (Integer) update);
            }
            pst.setString(2, gname);
            res = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (res == 0) {
            return false;
        } else {
            return true;
        }
    }


    public boolean deleteGoods(String gname) {
        conn = DBUtils.getConnection();
        String sql = "DELETE FROM GOODS WHERE gname = ?";
        int res = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, gname);
            res = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(res == 0){
            return false;
        }else {
            return true;
        }
    }
}
