package jp.co.zeta;

/**
 * Created by kang on 21/01/2017.
 */
public class Customer {
    private String name;
    private String address;
    private int id;
    private String type;
    private static int nextId = 0;

    public Customer(String name, String address) {
        this(name, address, "");
    }

    public Customer(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.id = ++nextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType() {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("编号:%04d\n姓名: %s\n住所: %s\n级别: %s", id, name, address, type);
    }
}
