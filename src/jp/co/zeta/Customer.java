package jp.co.zeta;

/**
 * Created by kang on 21/01/2017.
 */
public class Customer {
    public enum CustomerType {
        NONE, NORMAL, SILVER, GOLD;

        @Override
        public String toString() {
            String text = "";

            switch (this) {
                case GOLD:
                    text = "金卡会员";
                    break;
                case SILVER:
                    text = "银卡会员";
                    break;
                case NORMAL:
                    text = "普通会员";
                    break;
                case NONE:
                    text = "非会员";
                    break;
                default:
                    throw new IllegalArgumentException("无效会员类型：" + name());
            }

            return text;
        }
    }

    private String name = "";
    private String address = "";
    private int id;
    private CustomerType type = CustomerType.NONE;
    private static int nextId = 0;

    public Customer() {
        this.id = ++nextId;
    }

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
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

    public void setId(int id) {
        this.id = id;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("编号:%04d\n姓名: %s\n住所: %s\n级别: %s", id, name, address, type);
    }
}
