package jp.co.zeta;

import java.util.Scanner;

public class Counter {
    private Controller controller;

    public Counter(Controller controller) {
        this.controller = controller;
    }

    public void interact() {
        Scanner scanner = new Scanner(System.in);
        boolean finished = false;
        Customer customer = null;

        // customer
        while (!finished) {
            System.out.println("请选择输入顾客信息：\n1. 顾客ID\n2. 姓名/住址");

            String opt = scanner.next();
            switch (opt) {
                case "1":
                    System.out.println("请输入顾客ID：");
                    int id = scanner.nextInt();
                    customer = controller.getCustomer(id);
                    if (customer == null) {
                        System.out.println("顾客不存在，是否登录新顾客？\nY：是；其他输入：重新输入顾客信息");
                        String choice = scanner.next();
                        if (choice.compareToIgnoreCase("Y") == 0) {
                            System.out.println("请输入顾客姓名：");
                            String name = scanner.next();
                            System.out.println("请输入顾客住所：");
                            String address = scanner.next();
                            customer = new Customer(name, address);
                            controller.addCustomer(new Customer(name, address));
                            System.out.println("顾客新建成功！顾客信息：\n" + customer);
                            finished = true;
                        }
                    } else {
                        System.out.println(customer);
                        finished = true;
                    }
                    break;
                case "2":
                    System.out.println("请输入顾客姓名：");
                    String name = scanner.next();
                    System.out.println("请输入顾客住所：");
                    String address = scanner.next();
                    customer = controller.getCustomer(name, address);
                    if (customer == null) {
                        System.out.println("顾客不存在，是否登录新顾客？\nY：是；其他输入：重新输入顾客信息");
                        String choice = scanner.next();
                        if (choice.compareToIgnoreCase("Y") == 0) {
                            System.out.println("请输入顾客姓名：");
                            name = scanner.next();
                            System.out.println("请输入顾客住所：");
                            address = scanner.next();
                            customer = new Customer(name, address);
                            controller.addCustomer(new Customer(name, address));
                            System.out.println("顾客新建成功！顾客信息：\n" + customer);
                            finished = true;
                        }
                    } else {
                        System.out.println(customer);
                        finished = true;
                    }
                    break;
                default:
                    System.out.println("无效选项！请重试...\n");
                    break;
            }
        }

        // transaction
        finished = false;
        while (!finished) {
            double serviceExpense = 0, productExpense = 0;
            System.out.println("请输入顾客的服务消费金额：");
            if (scanner.hasNextDouble()) {
                serviceExpense = scanner.nextDouble();
            } else {
                System.out.println("输入无效，请重试！");
                scanner.next();
                continue;
            }
            System.out.println("请输入顾客的产品消费金额：");
            if (scanner.hasNextDouble()) {
                productExpense = scanner.nextDouble();
            } else {
                System.out.println("输入无效，请重试！");
                scanner.next();
                continue;
            }
            Transaction transaction = new Transaction(customer);
            transaction.setServiceExpense(serviceExpense);
            transaction.setProductExpense(productExpense);
            System.out.println(String.format("顾客本次消费：\n%s\n小计：¥%.2f（原价：%.2f）",
                    transaction, controller.calculate(transaction), transaction.getTotalExpense()));
            finished = true;
        }
    }
}
