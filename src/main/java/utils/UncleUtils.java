package utils;

import java.util.Scanner;

/**
 * Created by xiaomengning on 2017/10/3.
 */
public class UncleUtils {


    /**
     * 结束系统运行
     */

    public static void end(){
        System.exit(1);
    }



    /**
     *
     * @param msgy  输入y成功之后的提示信息
     * @param msgn  输入n成功之后的提示信息
     * @return true （输入y）false(输入n)
     */
    public static  boolean checkyn(String msgy,String msgn){
        boolean flag=true;
        boolean res=false;
        Scanner console = new Scanner(System.in);
        while(flag) {
            String yn = console.next();
            if (yn.length() == 1 && "yY".contains(yn)) {
                System.out.println(msgy);
                flag = false;
                res = true;
            } else if (yn.length() == 1 && "nN".contains(yn)) {
                System.out.println(msgn);
                flag=false;
            } else {
                System.out.println("输入有误，重新输入：");
            }
        }
        return res;
    }

}
