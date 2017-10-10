package entity;

/**
 * Created by xiaomengning on 2017/10/5.
 */
public class Gsales {
    private int gsid;
    private int gid;
    private int sid;

    private String gname;
    private int gnum;
    private float gprice;
    private int allSum;//单种商品销量总和

    public Gsales(int gnum, int sid, int gid) {
        this.gnum = gnum;
        this.sid = sid;
        this.gid = gid;
    }

    public Gsales(int gsid, int gnum, int gid, int sid) {
        this.gsid = gsid;
        this.gnum = gnum;
        this.gid = gid;
        this.sid = sid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Gsales(String gname, float gnrice, int gnum, int allSnum) {
        this.gname = gname;
        this.gprice = gnrice;
        this.gnum = gnum;
        this.allSum =allSnum;

    }

    public int getGsid() {
        return gsid;
    }

    public void setGsid(int gsid) {
        this.gsid = gsid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    public float getGprice() {
        return gprice;
    }

    public void setGprice(float gprice) {
        this.gprice = gprice;
    }

    public int getAllSum() {
        return allSum;
    }

    public void setAllSum(int allSum) {
        this.allSum = allSum;
    }
}
