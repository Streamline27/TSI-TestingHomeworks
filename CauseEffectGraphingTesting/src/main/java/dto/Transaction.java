package dto;

/**
 * Created by Vladislav on 5/31/2016.
 */
public class Transaction {
    private String debtor;
    private String creditor;
    private Double amount;

    public Transaction(String debtor, String creditor, Double amount) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.amount = amount;
    }

    public String getDebtor() {
        return debtor;
    }

    public void setDebtor(String debtor) {
        this.debtor = debtor;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject instanceof Transaction){
            Transaction that = (Transaction) anObject;
            if (!that.amount.equals(this.amount))     return false;
            if (!that.creditor.equals(this.creditor)) return  false;
            if (!that.debtor.equals(this.debtor))     return false;
            return true;
        }
        return false;
    }
}


