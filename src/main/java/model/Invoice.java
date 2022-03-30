package model;

import java.util.Date;

public class Invoice {
    private String id;
    private Date completionDate;
    private Date paymentDeadline;
    private long grandTotal;
    private long partnersId;

    // constructor without ID
    public Invoice(Date completionDate, Date paymentDeadline, long grandTotal, long partnersId) {
        this.completionDate = completionDate;
        this.paymentDeadline = paymentDeadline;
        this.grandTotal = grandTotal;
        this.partnersId = partnersId;
    }

    //constructor without ID and completion date
    public Invoice(Date paymentDeadline, long grandTotal, long partnersId) {
        this.paymentDeadline = paymentDeadline;
        this.grandTotal = grandTotal;
        this.partnersId = partnersId;
    }

    // constructor with ID
    public Invoice(String id, Date completionDate, Date paymentDeadline, long grandTotal, long partnersId) {
        this.id = id;
        this.completionDate = completionDate;
        this.paymentDeadline = paymentDeadline;
        this.grandTotal = grandTotal;
        this.partnersId = partnersId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public long getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(long grandTotal) {
        this.grandTotal = grandTotal;
    }

    public long getPartnersId() {
        return partnersId;
    }

    public void setPartnersId(long partnersId) {
        this.partnersId = partnersId;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", completionDate=" + completionDate +
                ", paymentDeadline=" + paymentDeadline +
                ", grandTotal=" + grandTotal +
                ", partnersId=" + partnersId +
                '}';
    }
}
