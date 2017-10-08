package page;


import java.util.Scanner;

/**
 * Created by xiaomengning on 2017/10/2.
 */
public class SalePage {

    Scanner console = new Scanner(System.in);

    public void showPage(){

        boolean flag1=true;

            System.out.println("超市购物管理系统>>前台收银系统");
            System.out.println("************************************");
            System.out.println("\t\t1.登录系统\n" +
                               "\t\t2.退出");
            System.out.println("************************************");
            System.out.println("请选择，输入数字：");
        while(flag1) {
            int num = console.nextInt();
            if (num == 1) {
                flag1=false;
                int count=3;
                boolean flag2=true;
                while(count>0 && flag2) {
                    System.out.println("请输入用户名：");
                    String sname = console.next();
                    System.out.println("请输入密码");
                    String spwd = console.next();
                    //TODO 查询数据库营业员表
                    boolean queryRes = true;
                    if (queryRes) { //用户名密码不正确
                        count--;
                        if(count>0){
                            System.out.println("用户名密码不匹配！" +
                                    "您还有"+count+"次机会，请重新输入：");
                        }else{
                            System.out.println("用户名和密码不匹配！");
                        }


                    }else{ //登录成功
                        flag2=false;
                        System.out.println("您已登录系统～");
                    }
                }
            } else if (num == 2) {
                flag1=false;
                System.out.println("您已退出系统～");
                System.exit(1);
            } else {
                System.out.println("输入有误，请重新输入");
            }
        }
    }
    public SalePage() {
        showPage();
    }

    public static void main(String[] args) {
        new SalePage();
    }
}
