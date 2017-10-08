package page;

import java.util.Scanner;

/**
 * Created by xiaomengning on 2017/10/2.
 * 系统主页面
 */
public class MainPage {

    public static void mainPage(){
        System.out.println("超市购物管理系统");
        System.out.println("************************************");
        System.out.println("\t\t1.商品维护\n" +
                           "\t\t2.前台收银\n" +
                           "\t\t3.商品管理");
        System.out.println("************************************");
        System.out.println("请选择，输入数字或者o退出：");
        Scanner console = new Scanner(System.in);
        while(true) {
            String input = console.next();
            if (input.length()==1 && "123o".contains(input)) { //正确输入
                switch (input.charAt(0)){
                    case '1': new GoodsPage();break;
                    case '2': new SalePage();break;
                    case '3': new SaleManPage();break;
                    case 'o': System.exit(1);
                }
            } else {
                System.out.println("输入有误，请重新输入：");
            }

        }

    }
    public static void main(String[] args) {
        MainPage.mainPage();
    }
}
