package jp.co.zeta.views;

import java.util.Scanner;

import jp.co.zeta.Controller;
import jp.co.zeta.Customer;

/**
 * Created by kang on 12/02/2017.
 */
public class InputCustomerView extends View {
    private String option;
    private int customerId;
    private String name;
    private String address;
    private Customer customer;

    public InputCustomerView(Controller controller) {
        super(controller);
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请选择输入顾客信息：\n1. 顾客ID\n2. 姓名/住址");
            option = scanner.next();

            switch (option) {
                case "1":
                    System.out.println("请输入顾客ID：");
                    if (scanner.hasNextInt()) {
                        customerId = scanner.nextInt();
                    }
                    break;
                case "2":
                    System.out.println("请输入顾客姓名：");
                    name = scanner.next();
                    System.out.println("请输入顾客住所：");
                    address = scanner.next();
                    break;
                default:
                    System.out.println("无效选项！请重试...\n");
                    break;
            }

            if (checkInput()) {
                break;
            } else {

            }
        }
    }

    @Override
    public boolean checkInput() {
        boolean result = false;

        // 输入顾客信息的方式
        result = (option.compareTo("1") == 0) || (option.compareTo("2") == 0);
        if (result) {
            // 检查顾客ID或者姓名/住所
            customer = controller.getCustomer(customerId);
            if (customer == null)
                customer = controller.getCustomer(name, address);
            result = customer != null;
        }

        return result;
    }
}
