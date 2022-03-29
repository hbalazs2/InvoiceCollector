package com.example.InvoiceCollector.model;

import java.util.Date;

public class Invoice {
    private long id;
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
    public Invoice(long id, Date completionDate, Date paymentDeadline, long grandTotal, long partnersId) {
        this.id = id;
        this.completionDate = completionDate;
        this.paymentDeadline = paymentDeadline;
        this.grandTotal = grandTotal;
        this.partnersId = partnersId;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public long getId() {
        return id;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    public long getGrandTotal() {
        return grandTotal;
    }

    public long getPartnersId() {
        return partnersId;
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
