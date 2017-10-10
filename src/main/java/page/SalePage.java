package page;


import dao.GoodsDao;
import dao.GsalesDao;
import dao.SalesManDao;
import entity.Goods;
import entity.Gsales;
import entity.SalesMan;

import utils.Arithmetic;
import utils.UncleUtils;

import java.util.*;

/**
 * Created by xiaomengning on 2017/10/2.
 * 前台收银页面
 */
public class SalePage {

    Scanner console = new Scanner(System.in);
    SalesManDao salesManDao = new SalesManDao();
    GoodsDao goodsDao = new GoodsDao();
    GsalesDao gsalesDao = new GsalesDao();

    public void showPage() {

        boolean flag1 = true;

        System.out.println("超市购物管理系统>>前台收银系统");
        System.out.println("************************************");
        System.out.println("\t\t1.登录系统\n" +
                "\t\t2.退出");
        System.out.println("************************************");
        System.out.println("请选择，输入数字：");
        while (flag1) {
            int num = console.nextInt();
            if (num == 1) {
                flag1 = false;
                int count = 3;
                boolean flag2 = true;
                while (count > 0 && flag2) {
                    System.out.println("请输入用户名：");
                    String sname = console.next();
                    System.out.println("请输入密码");
                    String spwd = console.next();

                    List<SalesMan> queryRes = salesManDao.query(new SalesMan(sname, spwd));
                    if (queryRes.size() == 0) { //用户名密码不正确
                        count--;
                        if (count > 0) {
                            System.out.println("用户名密码不匹配！" +
                                    "您还有" + count + "次机会，请重新输入：");
                        } else {
                            System.out.println("用户名和密码不匹配！");
                            System.exit(1);
                        }

                    } else { //登录成功
                        flag2 = false;
                        System.out.println("您已登录系统～");
                        settleMentPage(queryRes.get(0).getSid());
                    }
                }
            } else if (num == 2) {
                flag1 = false;
                System.out.println("您已退出系统～");
                System.exit(1);
            } else {
                System.out.println("输入有误，请重新输入");
            }
        }
    }

    private void settleMentPage(int sid) {
        System.out.println("*****************购物结算*****************");
        boolean flag = true;
        float needPay = 0.0f;
        int snum = 0;
        int gid = 0;

        Map<String,Object> needExecutor = new HashMap<String, Object>();
        List<Gsales> needUpdate = new ArrayList<Gsales>();

        while (flag) {
            System.out.println("请输入商品关键字:");
            String keyWords = console.next();
            List<Goods> queryRes = goodsDao.queryGoods(keyWords);
            System.out.println("商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            Map<String, Goods> goodMap = new HashMap<String, Goods>();
            for (Goods g : queryRes) {
                String gn = g.getGname();
                goodMap.put(gn, g);  //建立商品名字----》商品对象映射
                if (needExecutor.containsKey(gn)) { //之前买过
                    System.out.println(gn + "\t\t\t"
                            + g.getGprice() + "\t\t\t"
                            + needExecutor.get(gn) + "\n");
                } else {
                    System.out.println(g.toString());
                }
            }
            System.out.println("请选择商品:");
            String gname = console.next();
            Goods g = goodMap.get(gname);
            if (null == g) {
                System.out.println("无此商品，谢谢使用～");
                System.exit(1);
            }
            gid = g.getGid();
            int gnum = 0;
            int remain = 0;
            int inStore = 0;
            if(!needExecutor.containsKey(gname)){ //之前没买过
                inStore = g.getGnum();
            }else {
                inStore = (Integer) needExecutor.get(gname);
            }
            while (true) {
                System.out.println("请输入购买数量:");
                gnum = console.nextInt();

                if ((remain = inStore - gnum) < 0) { //库存不够
                    System.out.println("库存不足！，请调整购买数量。");
                } else {
                    break;
                }
            }

            needExecutor.put(gname, remain);
            needUpdate.add(new Gsales(gnum,sid,gid));

            float sum = Arithmetic.mul(g.getGprice(), gnum);
            needPay += sum;
            System.out.println(gname + "\t\t\t¥" + g.getGprice() + "\t\t\t购买数量" + gnum + "\t\t\t" + gname + "总价格" + sum);
            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");
        }


        System.out.println("总计:" + needPay);
        System.out.println("请输入实际交费金额:");
        float pay = console.nextFloat();
        float balance = Arithmetic.sub(pay, needPay);
        System.out.println("找钱：" + balance);

        goodsDao.updateGoods(needExecutor);
        gsalesDao.insertSales(needUpdate);

        System.out.println("谢谢光临！");
        System.exit(1);

    }

    public SalePage() {
        showPage();
    }

    public static void main(String[] args) {
        new SalePage();
    }
}
