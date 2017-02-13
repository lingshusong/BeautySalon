package jp.co.zeta;

/**
 * Created by kang on 21/01/2017.
 */
public class Main {
    public static void main(String args[]) {
        Controller controller = new Controller();
        Counter counter = new Counter(controller);
        //generateData(controller);
        controller.load();
        counter.interact();
    }

    private static void generateData(Controller controller) {
        controller.addCustomer(new Customer("张三丰", "武当山"));
        controller.addCustomer(new Customer("谢逊", "冰火岛"));
        controller.addCustomer(new Customer("李莫愁", "活死人墓"));
        controller.addCustomer(new Customer("黄药师", "桃花岛"));
    }
}
