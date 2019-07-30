package com.company.project.web;

public class IpStatistics {
   private String  type;
   private int used;
   private int unUsed;
   private int sum;

    public String getPrecent() {
        return precent;
    }

    public void setPrecent(String precent) {
        this.precent = precent;
    }

    private String precent;

    public IpStatistics(String type,int unUsed,int used,int sum,String precent){
        this.type=type;
        this.used = used;
        this.unUsed=unUsed;
        this.sum=sum;
        this.precent=precent;

    }



    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getUnUsed() {
        return unUsed;
    }

    public void setUnUsed(int unUsed) {
        this.unUsed = unUsed;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
