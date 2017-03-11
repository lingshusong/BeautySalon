package jp.co.zeta;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by kangyuzhe on 2017/03/11.
 */
public class DataHandler extends DefaultHandler {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private Object currentObject;
    private String currentElement;
    private XMLStreamWriter writer;

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void load() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new File("data.xml"), this);
    }

    public void save() throws Exception {
        writer = XMLOutputFactory.newInstance().createXMLStreamWriter(
                new OutputStreamWriter(new FileOutputStream(new File("data.xml")), "utf-8"));

        writer.writeStartDocument();
        writer.writeStartElement("beautysalon");
        writer.writeStartElement("customers");
        for (Customer c: customers) {
            writeCustomer(c);
        }
        writer.writeEndElement();

        writer.writeStartElement("transactions");
        for (Transaction t: transactions) {
            writeTransaction(t);
        }
        writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndDocument();

        writer.close();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if (currentElement.equals("customer")) {
            currentObject = new Customer();
        } else if (currentElement.equals("transaction")) {
            currentObject = new Transaction();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentObject instanceof Customer && !customers.contains(currentObject)) {
            customers.add((Customer)currentObject);
        } else if (currentObject instanceof Transaction && !transactions.contains(currentObject)) {
            transactions.add((Transaction)currentObject);
        }
        currentElement = "";
    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        String text = new String(chars, start, length);
        if (currentObject instanceof Customer) {
            Customer customer = (Customer)currentObject;
            if (currentElement.equals("id")) {
                customer.setId(Integer.parseInt(text));
            } else if (currentElement.equals("name")) {
                customer.setName(text);
            } else if (currentElement.equals("address")) {
                customer.setAddress(text);
            } else if (currentElement.equals("type")) {
                customer.setType(Customer.CustomerType.valueOf(text));
            }
        } else if (currentObject instanceof Transaction) {
            Transaction transaction = (Transaction)currentObject;
            if (currentElement.equals("customer_id")) {
                int custId = Integer.parseInt(text);
                Customer customer = null;
                for (Customer c: customers) {
                    if (c.getId() == custId) {
                        customer = c;
                        break;
                    }
                }
                transaction.setCustomer(customer);
            } else if (currentElement.equals("date")) {
                Date date;
                try {
                    date = DateFormat.getInstance().parse(text);
                } catch (Exception ex) {
                    date = new Date();
                }
                transaction.setDate(date);
            } else if (currentElement.equals("service_expense")) {
                double expense = Double.parseDouble(text);
                transaction.setServiceExpense(expense);
            } else if (currentElement.equals("product_expense")) {
                double expense = Double.parseDouble(text);
                transaction.setProductExpense(expense);
            }
        }
    }

    private void writeCustomer(Customer customer) throws Exception {
        writer.writeStartElement("customer");

        writer.writeStartElement("id");
        writer.writeCharacters(Integer.toString(customer.getId()));
        writer.writeEndElement();

        writer.writeStartElement("name");
        writer.writeCharacters(customer.getName());
        writer.writeEndElement();

        writer.writeStartElement("address");
        writer.writeCharacters(customer.getName());
        writer.writeEndElement();

        writer.writeStartElement("type");
        writer.writeCharacters(customer.getType().name());
        writer.writeEndElement();

        writer.writeEndElement();
    }

    private void writeTransaction(Transaction transaction) throws Exception {
        writer.writeStartElement("transaction");

        writer.writeStartElement("customer_id");
        writer.writeCharacters(Integer.toString(transaction.getCustomer().getId()));
        writer.writeEndElement();

        writer.writeStartElement("date");
        writer.writeCharacters(DateFormat.getInstance().format(transaction.getDate()));
        writer.writeEndElement();

        writer.writeStartElement("service_expense");
        writer.writeCharacters(Double.toString(transaction.getServiceExpense()));
        writer.writeEndElement();

        writer.writeStartElement("product_expense");
        writer.writeCharacters(Double.toString(transaction.getProductExpense()));
        writer.writeEndElement();

        writer.writeEndElement();
    }
}
