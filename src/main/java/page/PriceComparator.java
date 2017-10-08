package page;

import entity.Goods;
import utils.Arithmetic;

/**
 * Created by xiaomengning on 2017/10/8.
 */
public class PriceComparator implements java.util.Comparator<Goods> {
    public int compare(Goods o1, Goods o2) {
       if(Arithmetic.sub(o1.getGprice(),o2.getGprice()) > 0){
           return 1;
       }else {
           return -1;
       }
    }
}
