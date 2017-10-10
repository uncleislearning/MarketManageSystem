package entity;

/**
 * Created by xiaomengning on 2017/10/5.
 */
public class SalesMan {
    private int sid;
    private String sname;
    private String spwd;

    public SalesMan() {
    }

    public SalesMan(String sname, String spwd) {
        this.sname = sname;
        this.spwd = spwd;
    }

    public SalesMan(int sid, String sname, String spwd) {
        this.sid = sid;
        this.sname =sname;
        this.spwd = spwd;
    }


    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpwd() {
        return spwd;
    }

    public void setSpwd(String spwd) {
        this.spwd = spwd;
    }

    @Override
    public String toString() {
        return getSname()+"\t\t\t"+
                getSpwd()+"\n";
    }
}
