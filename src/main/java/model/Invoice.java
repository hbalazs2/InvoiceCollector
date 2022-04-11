package model;

import java.util.Date;

public class Invoice {
    private String id;
    private Date completionDate;
    private Date paymentDeadline;
    private long grandTotal;
    private boolean isIncoming;
    private boolean isOutgoing;
    private long partnersId;
    private long categoriesId;

    public Invoice(String id, Date paymentDeadline, long grandTotal, boolean isIncoming, boolean isOutgoing, long partnersId, long categoriesId) {
        this.id = id;
        this.paymentDeadline = paymentDeadline;
        this.grandTotal = grandTotal;
        this.isIncoming = isIncoming;
        this.isOutgoing = isOutgoing;
        this.partnersId = partnersId;
        this.categoriesId = categoriesId;
    }

    public Invoice(String id, Date completionDate, Date paymentDeadline, long grandTotal, boolean isIncoming, boolean isOutgoing, long partnersId, long categoriesId) {
        this.id = id;
        this.completionDate = completionDate;
        this.paymentDeadline = paymentDeadline;
        this.grandTotal = grandTotal;
        this.isIncoming = isIncoming;
        this.isOutgoing = isOutgoing;
        this.partnersId = partnersId;
        this.categoriesId = categoriesId;
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

    public boolean getIsIncoming() {
        return isIncoming;
    }

    public void setIsIncoming(boolean incoming) {
        this.isIncoming = incoming;
    }

    public boolean getIsOutgoing() {
        return isOutgoing;
    }

    public void setIsOutgoing(boolean outgoing) {
        this.isOutgoing = outgoing;
    }

    public long getPartnersId() {
        return partnersId;
    }

    public void setPartnersId(long partnersId) {
        this.partnersId = partnersId;
    }

    public long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(long categoriesId) {
        this.categoriesId = categoriesId;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", completionDate=" + completionDate +
                ", paymentDeadline=" + paymentDeadline +
                ", grandTotal=" + grandTotal +
                ", incoming=" + isIncoming +
                ", outgoing=" + isOutgoing +
                ", partnersId=" + partnersId +
                ", naturesId=" + categoriesId +
                "}\n";
    }
}
