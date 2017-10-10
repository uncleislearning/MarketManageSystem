package page;

import dao.GoodsDao;
import dao.SalesManDao;
import entity.Goods;
import entity.Gsales;
import entity.SalesMan;
import utils.UncleUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Created by xiaomengning on 2017/10/2.
 * 商品管理页面
 */
public class SaleManPage {
    Scanner console = new Scanner(System.in);

    GoodsDao goodsDao = new GoodsDao();
    SalesManDao salesManDao = new SalesManDao();

    private void showTodateSaleInfo() {
        System.out.println("执行列出当日卖出商品列表操作：");
        System.out.println("今日售出商品：\n" +
                "商品名称\t\t商品价格\t\t商品数量\t\t销量\t\t备注\n");
        List<Gsales> gsales = goodsDao.queryTodateSales();
        for (Gsales gs : gsales) {
            System.out.println(gs.getGname() + "\t\t\t" + gs.getGprice() + "\t\t\t" + gs.getGnum() + "\t\t\t" + gs.getAllSum());
        }
        System.out.println("请输入o返回！");
        while (true) {
            String s = console.next();
            if (s.equals("o")) {
               back();
                break;
            } else {
                System.out.println("输入有误请重新输入:");
            }
        }
    }

    private void addSaleMan() {
        System.out.println("执行添加售货员操作：\n");
        boolean flag = true;
        while (flag) {
            System.out.println("添加售货员姓名：");
            String sname = console.next();

            System.out.println("添加售货员登录密码：");
            String spwd = console.next();

            boolean res = salesManDao.addSaleMan(new SalesMan(sname, spwd));
            if (!res) {
                System.out.println("添加售货员账户失败！");
                System.exit(1);
            }

            System.out.println("添加成功!");

            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");
        }
        saleManManage();
    }

    private void updateSaleMan() {
        System.out.println("执行更改售货员账户操作：\n");
        boolean flag = true;
        while (flag) {
            System.out.println("输入更改售货员的姓名：");
            String sname = console.next();
            List<SalesMan> rs = salesManDao.query(sname);

            if (rs.size() == 0) {
                System.err.println("查无此人！");
            } else {
                System.out.println("营业员姓名\t\t营业员密码\n");
                System.out.println(rs.get(0).toString());
                System.out.println("选择您要更改的内容：\n" +
                        "1、更改营业员姓名：\n" +
                        "2、更改营业员密码：\n");

                String updateName;
                String updatePwd;
                String num;
                do {
                    num = console.next();
                    if (!num.matches("[1-2]")) {
                        System.out.println("输入有误！请重新输入：");
                    } else {
                        break;
                    }
                } while (true);

                int n = Integer.valueOf(num);
                boolean res = false;
                switch (n) {
                    case 1:
                        System.out.println("请输入已更改营业员姓名");
                        updateName = console.next();
                        res = salesManDao.updateSalesMan(n, new SalesMan(rs.get(0).getSid(), updateName, rs.get(0).getSpwd()));
                        break;
                    case 2:
                        System.out.println("请输入已更改营业员密码");
                        updatePwd = console.next();
                        res = salesManDao.updateSalesMan(n, new SalesMan(rs.get(0).getSid(), rs.get(0).getSname(), updatePwd));
                        break;
                }
                if (!res) {
                    System.out.println("更改操作失败！");
                    System.exit(1);
                }
                System.out.println("操作成功～");
            }
            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");

        }

        saleManManage();
    }

    private void deleteSaleMan() {
        System.out.println("执行删除售货员操作：\n");
        boolean flag = true;
        while (flag) {
            System.out.println("输入删除的售货员姓名：");
            String sname = console.next();
            List<SalesMan> res = salesManDao.query(sname);
            if (res.size() == 0) {
                System.err.println("查无此人！");
            } else {
                System.out.println("售货员姓名\t\t售货员密码\n");
                System.out.println(res.get(0).toString());

                System.out.println("是否确定要删除(y/n)?：");
                boolean yn = UncleUtils.checkyn("", "删除已取消");
                if (yn) {  //输入y ,删除数据库中的该账户
                    boolean rs = salesManDao.deleteSalesMan(res.get(0).getSid());
                    if (rs) {
                        System.out.println("删除成功！");
                    } else {
                        System.out.println("删除操作失败！");
                    }
                }
            }
            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");
        }
        saleManManage();
    }

    private void displaySaleMan() {
        System.out.println("显示所有售货员：\n");
        List<SalesMan> res = salesManDao.query();
        System.out.println("售货员姓名\t\t售货员密码\t\t备注\n");
        for (SalesMan s : res) {
            System.out.println(s.toString());
        }
        System.out.println("请输入o返回！");
        while (true) {
            String s = console.next();
            if (s.equals("o")) {
                saleManManage();
                break;
            } else {
                System.out.println("输入有误请重新输入:");
            }
        }
    }

    private void querySaleMan() {

        boolean flag = true;
        while (flag) {
            System.out.println("请输入售货员名称:");
            String gname = console.next();
            List<SalesMan> result = salesManDao.query(gname);

            if(result.size()==0){
                System.err.println("查无此人！");
            }else {
                System.out.println("售货员名称\t\t售货员密码\t\t备注\n");
                for (SalesMan s : result) {
                    System.out.println(s.toString());
                }
            }
            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");
        }
        saleManManage();
    }

    private void back() {
        showPage();
    }

    private void end() {
        System.exit(1);
    }

    public void saleManManage() {
        System.out.println("超市购物管理系统>>商品管理售货员管理>>售货员管理\n");
        System.out.println("************************************");
        System.out.println("\t\t1.添加售货员\n" +
                "\t\t2.更改售货员\n" +
                "\t\t3.删除售货员\n" +
                "\t\t4.显示所售货员\n" +
                "\t\t5.查询售货员");
        System.out.println("************************************");
        System.out.println("请选择，输入数字或者按o返回上一级菜单：");
        while (true) {
            String input = console.next();
            if (input.length() == 1 && "12345o".contains(input)) { //正确输入
                switch (input.charAt(0)) {
                    case '1':
                        addSaleMan();
                        break;
                    case '2':
                        updateSaleMan();
                        break;
                    case '3':
                        deleteSaleMan();
                        break;
                    case '4':
                        displaySaleMan();
                        break;
                    case '5':
                        querySaleMan();
                        break;
                    case 'o':
                        back();
                        break;
                }
                break;
            } else {
                System.out.println("输入有误，请重新输入：");
            }

        }
    }

    public void showPage() {
        System.out.println("超市购物管理系统>>商品管理\n");
        System.out.println("************************************");
        System.out.println("\t\t1.列出当日卖出商品列表\n" +
                "\t\t2.售货员管理");
        System.out.println("************************************");
        System.out.println("请选择，输入数字或者o返回上一级菜单：");
        while (true) {
            String input = console.next();
            if (input.length() == 1 && "12o".contains(input)) { //正确输入
                switch (input.charAt(0)) {
                    case '1':
                        showTodateSaleInfo();
                        break;
                    case '2':
                        saleManManage();
                        break;
                    case 'o':
                        MainPage.mainPage();
                        break;
                }
            } else {
                System.out.println("输入有误，请重新输入：");
            }

        }
    }

    public SaleManPage() {
        showPage();
    }

    public static void main(String[] args) {
        new SaleManPage();
    }
}
