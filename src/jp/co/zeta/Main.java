package jp.co.zeta;

/**
 * Created by kang on 21/01/2017.
 */
public class Main {
    public static void main(String args[]) {
        Controller controller = new Controller();
        Counter counter = new Counter(controller);
        generateData(controller);
        counter.interact();
    }

    private static void generateData(Controller controller) {
        controller.addCustomer(new Customer("张三丰", "武当山", "gold"));
        controller.addCustomer(new Customer("谢逊", "冰火岛", "silver"));
        controller.addCustomer(new Customer("李莫愁", "活死人墓", "normal"));
        controller.addCustomer(new Customer("黄药师", "桃花岛"));
    }
}
