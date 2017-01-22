package jp.co.zeta;

import java.util.Scanner;

public class Counter {
    private Controller controller;

    public Counter(Controller controller) {
        this.controller = controller;
    }

    public void interact() {
        Scanner scanner = new Scanner(System.in);
        boolean isInvalidInput = false;


        while (!isInvalidInput) {
            Customer customer = null;
            System.out.println("请选择输入客户信息：\n1. 客户ID\n2. 姓名/住址");

            String opt = scanner.next();
            switch (opt) {
                case "1":
                    System.out.println("请输入客户ID：");
                    int id = scanner.nextInt();
                    customer = controller.getCustomer(id);
                    if (customer == null) {
                        // TODO: create customer
                    } else {
                        System.out.println(customer);
                        isInvalidInput = true;
                    }
                    break;
                case "2":
                    System.out.println("请输入客户姓名：");
                    String name = scanner.next();
                    System.out.println("请输入客户住所：");
                    String address = scanner.next();
                    customer = controller.getCustomer(name, address);
                    if (customer == null) {
                        // TODO: create customer
                    } else {
                        System.out.println(customer);
                        isInvalidInput = true;
                    }
                    break;
                default:
                    System.out.println("无效选项！请重试...\n");
                    break;
            }
        }

        // TODO: next: prompt to input expense
    }

    private void getCustomerFromInputId() {
        System.out.println();
    }

    private void getCustomerFromInputNameAddress() {

    }
}
