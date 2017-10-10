package dao;

import entity.Goods;
import entity.Gsales;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by xiaomengning on 2017/10/2.
 */
public class GoodsDao {
    private static Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;


    public boolean addGoods(Goods goods) {
        conn = DBUtils.getConnection();
        String sql = "INSERT INTO GOODS(GNAME,GPRICE,GNUM) VALUES(?,?,?)";
        int res = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, goods.getGname());
            pst.setFloat(2, goods.getGprice());
            pst.setInt(3, goods.getGnum());
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

//重构代码，极大的化简冗余
    private List<Goods> queryGoods(String sql,String param) {
        List res = new ArrayList();
        conn = DBUtils.getConnection();
        try {
            pst = conn.prepareStatement(sql);
            if(!StringUtils.isEmpty(param)){
                pst.setString(1,param);
            }
            rs = pst.executeQuery();
            if (null != rs) {
                while (rs.next()) {
                    int id = rs.getInt("gid");
                    float price = rs.getFloat("gprice");
                    String name = rs.getString("gname");
                    int num = rs.getInt("gnum");

                    Goods g = new Goods(id,name,price,num);
                    res.add(g);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            DBUtils.closeAll(rs,pst,conn);
        }

        return res;
    }


    /**
     *  查询goods表中所有商品
     * @return
     */
    public List<Goods> queryGoods() {
        String sql = "SELECT * FROM GOODS";
        return queryGoods(sql,"");
    }


    /**
     * 查询goods表中指定名字的商品
     * @param goods
     * @return
     */
    public List<Goods> queryGoods(Goods goods) {
        String sql = "SELECT * FROM GOODS WHERE gname=?";
        return queryGoods(sql,goods.getGname());
    }

    /**
     * 根据商品名称关键字查找商品
     * @param keyWords
     * @return
     */

    public List<Goods> queryGoods(String keyWords) {
        String sql = "SELECT * FROM GOODS WHERE GNAME LIKE ?";
        return queryGoods(sql,"%"+keyWords+"%");
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
        }finally {
            DBUtils.closeAll(null,pst,conn);
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
        }finally {
            DBUtils.closeAll(null,pst,conn);
        }

        if(res == 0){
            return false;
        }else {
            return true;
        }
    }


    public void updateGoods(Map<String,Object> needExecutor) {

        conn = DBUtils.getConnection();
        String sql = "UPDATE GOODS SET gnum = ? WHERE gname = ?";
        try {
            pst = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            Set<String> keys = needExecutor.keySet();
            for( String p : keys){
               pst.setInt(1,(Integer) needExecutor.get(p));
               pst.setString(2,p);
               pst.addBatch();
            }
            pst.executeBatch();
            conn.commit();//手动提交
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                if(!conn.isClosed()){
                    conn.rollback();
                    System.out.println("插入失败！回滚");
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

    /**
     * 查找gsale表中当日卖出的记录
     * @return
     */
    public List<Gsales> queryTodateSales() {
        List res = new ArrayList();
        conn = DBUtils.getConnection();
        String  sql = "select gname,gprice,gnum, allSum from goods, (select gid as salesid,sum(gnum) as allSum from gsales where to_days(sdate) =to_days(now()) group by gid) as ano where gid = salesid";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next())
            {
                String gName = rs.getString(1);
                float gPrice = rs.getFloat(2);
                int gNum = rs.getInt(3);
                int allSnum = rs.getInt("allSum");

                Gsales Gsales = new Gsales(gName,gPrice,gNum,allSnum);
                res.add(Gsales);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            DBUtils.closeAll(rs,pst,conn);
        }
        return res;
    }




}
