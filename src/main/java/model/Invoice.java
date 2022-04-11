package model;

import java.util.Date;

public class Invoice {
    private String id;
    private Date creationDate;
    private Date completionDate;
    private Date paymentDeadline;
    private long grandTotal;
    private boolean isIncoming;
    private boolean isOutgoing;
    private long partnersId;
    private long categoriesId;

    public Invoice(String id, Date creationDate, Date paymentDeadline, long grandTotal, boolean isIncoming, boolean isOutgoing, long partnersId, long categoriesId) {
        this.id = id;
        this.creationDate = creationDate;
        this.paymentDeadline = paymentDeadline;
        this.grandTotal = grandTotal;
        this.isIncoming = isIncoming;
        this.isOutgoing = isOutgoing;
        this.partnersId = partnersId;
        this.categoriesId = categoriesId;
    }

    public Invoice(String id, Date creationDate, Date completionDate, Date paymentDeadline, long grandTotal, boolean isIncoming, boolean isOutgoing, long partnersId, long categoriesId) {
        this.id = id;
        this.creationDate = creationDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public boolean isIncoming() {
        return isIncoming;
    }

    public void setIncoming(boolean incoming) {
        isIncoming = incoming;
    }

    public boolean isOutgoing() {
        return isOutgoing;
    }

    public void setOutgoing(boolean outgoing) {
        isOutgoing = outgoing;
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
                "id=" + id +
                ", creationDate=" + creationDate +
                ", completionDate=" + completionDate +
                ", paymentDeadline=" + paymentDeadline +
                ", grandTotal=" + grandTotal +
                ", isIncoming=" + isIncoming +
                ", isOutgoing=" + isOutgoing +
                ", partnersId=" + partnersId +
                ", categoriesId=" + categoriesId +
                "}\n";
    }
}
