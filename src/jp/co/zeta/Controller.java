package jp.co.zeta;

import java.util.ArrayList;

/**
 * Created by kang on 21/01/2017.
 */
public class Controller {
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();

    public double calculate(Transaction transaction) {
        double result = 0.0;

        Customer customer = transaction.getCustomer();
        double serviceRate = DiscountRate.getServiceRate(customer.getType());
        double productRate = DiscountRate.getProductRate(customer.getType());

        result = transaction.getServiceExpense() * (1 - serviceRate) + transaction.getProductExpense() * (1 - productRate);

        return result;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Customer getCustomer(int id) {
        Customer customer = null;

        for (Customer c: customers) {
            if (c.getId() == id) {
                customer = c;
                break;
            }
        }

        return customer;
    }

    public Customer getCustomer(String name, String address) {
        Customer customer = null;

        for (Customer c: customers) {
            if (c.getName().equalsIgnoreCase(name) && c.getAddress().equalsIgnoreCase(address)) {
                customer = c;
                break;
            }
        }

        return customer;
    }
}
