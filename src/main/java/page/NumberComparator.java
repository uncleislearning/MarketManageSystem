package page;

import entity.Goods;

import java.util.Comparator;

/**
 * Created by xiaomengning on 2017/10/8.
 */
public class NumberComparator implements Comparator<Goods>{

    public int compare(Goods o1, Goods o2) {
        return o1.getGnum()-o2.getGnum();
    }

}
