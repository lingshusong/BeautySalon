package jp.co.zeta;

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

    public boolean load() {
        boolean result = false;

        try {
            // load customers
            Scanner scanner = new Scanner(new File("Customers.txt"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] properties = line.split("\t");
                int id = properties.length > 0 ? Integer.parseInt(properties[0]) : 0;
                String name = properties.length > 1 ? properties[1] : "";
                String address = properties.length > 2 ? properties[2] : "";
                String type = properties.length > 3 ? properties[3] : "";
                Customer customer = new Customer(name, address);
                customer.setType(Customer.CustomerType.valueOf(type));
                customers.add(customer);
            }
            // load transactions
            scanner = new Scanner(new File("Transactions.txt"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] properties = line.split("\t");
                int id = properties.length > 0 ? Integer.parseInt(properties[0]) : 0;
                String date = properties.length > 1 ? properties[1] : "";
                double serviceExpense = properties.length > 2 ? Double.parseDouble(properties[2]) : 0;
                double productExpense = properties.length > 3 ? Double.parseDouble(properties[3]) : 0;
                Transaction transaction = new Transaction(getCustomer(id), DateFormat.getInstance().parse(date));
                transaction.setServiceExpense(serviceExpense);
                transaction.setProductExpense(productExpense);
                transactions.add(transaction);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public boolean save() {
        boolean result = false;

        // save customers
        try {
            Formatter formatter = new Formatter(new File("Customers.txt"));
            for (Customer customer: customers) {
                formatter.format("%d\t%s\t%s\t%s\n", customer.getId(), customer.getName(),
                        customer.getAddress(), customer.getType().name());
            }
            formatter.flush();
            formatter.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // save transactions
        try {
            Formatter formatter = new Formatter(new File("Transactions.txt"));
            for (Transaction transaction: transactions) {
                formatter.format("%d\t%s\t%f\t%f\n", transaction.getCustomer().getId(),
                        DateFormat.getInstance().format(transaction.getDate()),
                        transaction.getServiceExpense(), transaction.getProductExpense());
            }
            formatter.flush();
            formatter.close();
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
