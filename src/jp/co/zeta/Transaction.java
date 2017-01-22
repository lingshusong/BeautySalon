package jp.co.zeta;

import java.util.Date;

/**
 * Created by kang on 21/01/2017.
 */
public class Transaction {
    private Customer customer;
    private Date date;
    private double serviceExpense = 0.0;
    private double productExpense = 0.0;

    public Transaction(Customer customer) {
        this(customer, new Date());
    }

    public Transaction(Customer customer, Date date) {
        this.customer = customer;
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getServiceExpense() {
        return serviceExpense;
    }

    public void setServiceExpense(double expense) {
        serviceExpense = expense;
    }

    public double getProductExpense() {
        return productExpense;
    }

    public void setProductExpense(double expense) {
        productExpense = expense;
    }

    public double getTotalExpense() {
        return serviceExpense + productExpense;
    }

    @Override
    public String toString() {
        // TODO: format transaction info
        return "";
    }
}
