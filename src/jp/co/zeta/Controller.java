package jp.co.zeta;

import jdk.internal.org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by kang on 21/01/2017.
 */
public class Controller {
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    DataHandler dataHandler = new DataHandler();

    public boolean load() {
        boolean result = true;

        try {
            dataHandler.load();
            customers = dataHandler.getCustomers();
            transactions = dataHandler.getTransactions();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = false;
        }

        return result;
    }

    public boolean save() {
        boolean result = false;

        try {
            dataHandler.save();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

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
        Customer.CustomerType type = getCustomerType(transaction.getCustomer());
        transaction.getCustomer().setType(type);
        System.out.println(String.format("Customer ID: %04d, Type: %s", transaction.getCustomer().getId(), type));
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

    private Customer.CustomerType getCustomerType(Customer customer) {
        Customer.CustomerType type = Customer.CustomerType.NONE;

        double expense = getTotalExpenseAmount(customer);
        if (expense >= 1e5) {
            type = Customer.CustomerType.GOLD;
        } else if (expense >= 5e4) {
            type = Customer.CustomerType.SILVER;
        } else if (expense >= 1e4) {
            type = Customer.CustomerType.NORMAL;
        }

        return type;
    }

    private double getTotalExpenseAmount(Customer customer) {
        double result = 0.0;

        for (Transaction transaction: transactions) {
            if (transaction.getCustomer().getId() == customer.getId()) {
                result += transaction.getTotalExpense();
            }
        }

        return result;
    }
}
