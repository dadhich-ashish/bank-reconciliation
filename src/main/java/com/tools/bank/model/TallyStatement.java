package com.tools.bank.model;

import java.util.Date;

public class TallyStatement {
    private Date date;
    private String particulars;
    private String vchType;
    private String vchNo;
    private double debit;
    private double credit;

    public TallyStatement(Date date2, String particulars2, String vchType2, String vchNo2, double debit2,
            double credit2) {
        this.date = date2;  
        this.particulars = particulars2;
        this.vchType = vchType2;
        this.vchNo = vchNo2;
        this.debit = debit2;
        this.credit = credit2;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getVchType() {
        return vchType;
    }

    public void setVchType(String vchType) {
        this.vchType = vchType;
    }

    public String getVchNo() {
        return vchNo;
    }

    public void setVchNo(String vchNo) {
        this.vchNo = vchNo;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
    
    
    @Override
    public String toString() {
        return "TallyStatement{" +
        "date='" + date + '\'' +
        ", particulars='" + particulars + '\'' +
        ", vchType='" + vchType + '\'' +
        ", vchNo='" + vchNo + '\'' +
        ", debit=" + debit +
        ", credit=" + credit +
        '}';
    }
}
