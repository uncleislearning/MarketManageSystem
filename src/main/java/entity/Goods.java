package entity;

/**
 * Created by xiaomengning on 2017/10/5.
 */
public class Goods{
    private int gid;
    private String gname;
    private float gprice;
    private int gnum;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public float getGprice() {
        return gprice;
    }

    public void setGprice(float gprice) {
        this.gprice = gprice;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }


    @Override
    public String toString() {
        return (getGname() + "\t\t\t"
                + getGprice() + "\t\t\t"
                + getGnum() + "\n");
    }

//    public int compareTo(Goods o) {
//        return this.getGnum() - o.getGnum();
//    }
}
