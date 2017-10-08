package page;

import utils.UncleUtils;

import java.util.Scanner;

/**
 * Created by xiaomengning on 2017/10/2.
 */
public class SaleManPage {
    Scanner console = new Scanner(System.in);


    private void showTodateSaleInfo(){
        System.out.println("执行列出当日卖出商品列表操作：");
        System.out.println("今日售出商品：\n" +
                "商品名称\t\t商品价格\t\t商品数量\t\t销量\t\t备注\n");
        //查询数据库

        end();
    }

    private void addSaleMan(){
        System.out.println("执行添加售货员操作：\n");
        boolean flag=true;
        while(flag) {
            System.out.println("添加售货员姓名：");
            String sname = console.next();

            System.out.println("添加售货员登录密码：");
            String spwd = console.next();

            //TODO  添加营业员到数据库---营业员表
            System.out.println("商品添加成功～");

            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");
        }
        System.out.println("已退出系统～");
    }
    private void updateSaleMan(){
        System.out.println("执行更改售货员账户操作：\n");
        boolean flag = true;
        while(flag) {
            System.out.println("输入更改售货员的姓名：");
            String gname = console.next();
            //TODO  搜索数据库
            System.out.println("营业员姓名\t\t营业员密码\n");
            //TODO  显示
            System.out.println("选择您要更改的内容：\n" +
                    "1、更改营业员姓名：\n" +
                    "2、更改营业员密码：\n");
            int num = console.nextInt();
            String uodateName;
            String updatePwd;
            switch (num) {
                case 1:
                    System.out.println("请输入已更改营业员姓名");
                    uodateName = console.next();
                    break;
                case 2:
                    System.out.println("请输入已更改营业员密码");
                    updatePwd = console.next();
                    break;
            }
            //TODO 将修改信息保存到数据库
            System.out.println("操作成功～");

            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("","");
        }

        System.out.println("已退出系统～");
    }
    private void deleteSaleMan(){
        System.out.println("执行删除售货员操作：\n");
        boolean flag = true;
        while(flag) {
            System.out.println("输入删除的售货员姓名：");
            String sname = console.next();
            //TODO 搜索数据库，
            System.out.println("售货员姓名\t\t售货员密码\n");
            //TODO 显示
            System.out.println("是否确定要删除(y/n)?：");

            UncleUtils.checkyn("删除已成功！", "删除已取消");
            //TODO 将数据库中对应的商品信息条目删除
            System.out.println("是否继续(y/n)：");
            flag = UncleUtils.checkyn("", "");
        }
        System.out.println("已退出系统～");
    }
    private void displaySaleMan(){
        System.out.println("显示所有售货员：\n");
        //TODO 查询数据库
        System.out.println("售货员姓名\t\t售货员密码\t\t备注\n");
        //TODO 显示
    }

    private void querySaleMan(){

    }
    private void back(){
        showPage();
    }
    private void end(){
        System.exit(1);
    }

    public void saleManManage(){
        System.out.println("超市购物管理系统>>商品管理售货员管理>>售货员管理\n");
        System.out.println("************************************");
        System.out.println("\t\t1.添加售货员\n" +
                           "\t\t2.更改售货员\n" +
                           "\t\t3.删除售货员\n" +
                           "\t\t4.显示所售货员\n" +
                           "\t\t5.查询售货员");
        System.out.println("************************************");
        System.out.println("请选择，输入数字或者按o返回上一级菜单：");
        while(true) {
            String input = console.next();
            if (input.length()==1 && "12345o".contains(input)) { //正确输入
                switch (input.charAt(0)){
                    case '1': addSaleMan();break;
                    case '2': updateSaleMan();break;
                    case '3': deleteSaleMan();break;
                    case '4': displaySaleMan();break;
                    case '5': querySaleMan();break;
                    case 'o': back();break;
                }
            } else {
                System.out.println("输入有误，请重新输入：");
            }

        }
    }

    public void showPage(){
        System.out.println("超市购物管理系统>>商品管理\n");
        System.out.println("************************************");
        System.out.println("\t\t1.列出当日卖出商品列表\n" +
                            "\t\t2.售货员管理");
        System.out.println("************************************");
        System.out.println("请选择，输入数字或者o返回上一级菜单：");
        while(true) {
            String input = console.next();
            if (input.length()==1 && "12o".contains(input)) { //正确输入
                switch (input.charAt(0)){
                    case '1': showTodateSaleInfo();break;
                    case '2': saleManManage();break;
                    case 'o': MainPage.mainPage();break;
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
