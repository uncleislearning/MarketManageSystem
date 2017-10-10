package page;


import dao.GoodsDao;
import entity.Goods;

import utils.UncleUtils;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by xiaomengning on 2017/10/2.
 * 商品维护页面
 */
public class GoodsPage {

    private static GoodsDao goodsDao = new GoodsDao();

    Scanner console = new Scanner(System.in);

    private void addGoods() {
        System.out.println("执行添加商品操作：\n");
        boolean flag = true;
        while (flag) {
            System.out.println("添加商品名称：");
            String gname = console.next();

            System.out.println("添加商品价格：");
            float gprice = console.nextFloat();

            System.out.println("添加商品数量：");
            int gcount = console.nextInt();

            boolean res = goodsDao.addGoods(new Goods(gname, gprice, gcount));
            if (!res) {
                System.out.println("商品添加失败！");
                System.exit(1);
            } else {
                System.out.println("商品添加成功～");
                System.out.println("是否继续(y/n)：");
                flag = UncleUtils.checkyn("", "");
            }
        }
        back();
    }

    private void updateGoods() {

        System.out.println("执行更改商品操作：\n");
        boolean flag = true;
        while (flag) {
            System.out.println("输入更改商品的名称：");
            String gname = console.next();

            List<Goods> result = goodsDao.queryGoods(new Goods(gname));

            if (result.size() == 0) {
                System.out.println("不存在该商品！");
            } else {
                System.out.println("商品名称\t\t商品价格\t\t商品数量\n");
                for (Goods g : result) {
                    System.out.println(g.toString());
                }
                System.out.println("选择您要更改的内容：\n" +
                        "1、更改商品名称：\n" +
                        "2、更改商品价格：\n" +
                        "3、更改商品数量：");
                int num = console.nextInt();

                boolean res = false;
                switch (num) {
                    case 1:
                        System.out.println("请输入已更改商品名称");
                        String updateName = console.next();
                        res = goodsDao.updateGoods(updateName, gname);
                        break;
                    case 2:
                        System.out.println("请输入已更改商品价格");
                        float updatePrice = console.nextFloat();
                        res = goodsDao.updateGoods((Float) updatePrice, gname);
                        break;
                    case 3:
                        System.out.println("请输入已更改商品数量");
                        int updateCount = console.nextInt();
                        res = goodsDao.updateGoods(updateCount, gname);
                        break;
                }
                if (!res) {
                    System.out.println("更改失败！");
                    UncleUtils.end();
                } else {
                    System.out.println("更改成功！");
                }

            }
            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");

        }
        back();
    }

    private void deleteGoods() {

        System.out.println("执行删除商品操作：\n");
        boolean flag = true;
        while (flag) {
            System.out.println("输入删除商品的名称：");
            String gname = console.next();

            List<Goods> result = goodsDao.queryGoods(new Goods(gname));

            if (result.size() == 0) {
                System.out.println("不存在该商品！");
            } else {
                System.out.println("商品名称\t\t商品价格\t\t商品数量\n");
                for (Goods g : result) {
                    System.out.println(g.toString());
                }
                System.out.println("是否确定要删除(y/n)?：");
                if (UncleUtils.checkyn("", "删除操作已取消")) { //输入y，执行删除语句
                    boolean res = goodsDao.deleteGoods(gname);
                    if (res) {
                        System.out.println("删除成功！");
                    } else {
                        System.out.println("删除失败！");
                    }
                }

            }
            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");
        }
        back();
    }

    private void displayGoods() {

        System.out.println("显示所有商品：\n");
        List<Goods> result = goodsDao.queryGoods();
        System.out.println("商品名称\t\t商品价格\t\t商品数量\n");
        for (Goods g : result) {
            System.out.println(g.toString());
        }
        back();
    }

    private void queryGoods() {
        System.out.println("执行查询商品操作：\n");
        System.out.println(
                        "1、按商品数量升序查询：\n" +
                        "2、按商品价格升序查询：\n" +
                        "3、输入关键字查询商品：");
        System.out.println("请选择，输入数字或者按o返回上一级菜单：");

        while (true) {
            String input = console.next();
            if (input.length() == 1 && "123o".contains(input)) { //正确输入
                char ch = input.charAt(0);
                switch (ch) {
                    case 'o':
                        back();
                        break;
                    case '1':
                    case '2':
                    case '3':
                        if (ch == '1') {
                            showUpbyNumber();
                        } else if (ch == '2') {
                            showUpByPrice();
                        } else {
                            showByKeyWord();
                        }
                      back();
                }
            } else {
                System.out.println("输入有误，请重新输入：");
            }

        }
    }


    private void back() {
        MainPage.mainPage();
    }

    public void showUpbyNumber() {
        System.out.println("商品名称\t\t商品价格\t\t商品数量\n");
        List<Goods> result = goodsDao.queryGoods();
        Collections.sort(result, new NumberComparator());
        for (Goods g : result) {
            System.out.println(g.toString());
        }

    }

    public void showUpByPrice() {
        System.out.println("商品名称\t\t商品价格\t\t商品数量\n");
        List<Goods> result = goodsDao.queryGoods();
        Collections.sort(result, new PriceComparator());
        for (Goods g : result) {
            System.out.println(g.toString());
        }
    }

    public void showByKeyWord() {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入商品名称:");
            String gname = console.next();
            List<Goods> result = goodsDao.queryGoods(new Goods(gname));
            System.out.println("商品名称\t\t商品价格\t\t商品数量\n");
            for (Goods g : result) {
                System.out.println(g.toString());
            }
            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");
        }
    }

    private void showPage() {
        System.out.println("超市购物管理系统>>商品维护");
        System.out.println("************************************");
        System.out.println("\t\t1.添加商品\n" +
                "\t\t2.更改商品\n" +
                "\t\t3.删除商品\n" +
                "\t\t4.显示所有商品\n" +
                "\t\t5.查询商品");
        System.out.println("************************************");
        System.out.println("请选择，输入数字或者按o返回上一级菜单：");

        while (true) {
            String input = console.next();
            if (input.length() == 1 && "12345o".contains(input)) { //正确输入
                switch (input.charAt(0)) {
                    case '1':
                        addGoods();
                        break;
                    case '2':
                        updateGoods();
                        break;
                    case '3':
                        deleteGoods();
                        break;
                    case '4':
                        displayGoods();
                        break;
                    case '5':
                        queryGoods();
                        break;
                    case 'o':
                        back();
                        break;
                }
            } else {
                System.out.println("输入有误，请重新输入：");
            }

        }
    }

    public GoodsPage() {
        showPage();
    }

    public static void main(String[] args) {
        new GoodsPage();
    }
}
