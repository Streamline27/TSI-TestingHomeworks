package dto;

/**
 * Created by Vladislav on 5/31/2016.
 */

public class Expense {
    private Long expenseId;
    private String name;
    private String service;
    private Double amount;

    private Expense() {
    }

    public Expense(String name, String service, Double amount) {
        this.name = name;
        this.service = service;
        this.amount = amount;
    }



    public String getName() {
        return name;
    }

    public String getService() {
        return service;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getId() {
        return expenseId;
    }

    public void setId(Long expenseId) {
        this.expenseId = expenseId;
    }
}
