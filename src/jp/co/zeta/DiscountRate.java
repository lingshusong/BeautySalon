package jp.co.zeta;

/**
 * Created by kang on 21/01/2017.
 */
public class DiscountRate {
    // service discount rate
    public static double goldServiceRate = 0.2;
    public static double silverServiceRate = 0.15;
    public static double normalServiceRate = 0.1;
    public static double noneServiceRate = 0.0;
    // product discount rate
    public static double goldProductRate = 0.1;
    public static double silverProductRate = 0.1;
    public static double normalProductRate = 0.1;
    public static double noneProductRate = 0.1;

    public static double getServiceRate(Customer.CustomerType customerType) {
        double serviceRate = 0.0;

        switch (customerType) {
            case GOLD:
                serviceRate = DiscountRate.goldServiceRate;
                break;
            case SILVER:
                serviceRate = DiscountRate.silverServiceRate;
                break;
            case NORMAL:
                serviceRate = DiscountRate.normalServiceRate;
                break;
            default:
                serviceRate = DiscountRate.noneServiceRate;
                break;
        }

        return serviceRate;
    }

    public static double getProductRate(Customer.CustomerType customerType) {
        double productRate = 0.0;

        switch (customerType) {
            case GOLD:
                productRate = DiscountRate.goldProductRate;
                break;
            case SILVER:
                productRate = DiscountRate.silverProductRate;
                break;
            case NORMAL:
                productRate = DiscountRate.normalProductRate;
                break;
            default:
                productRate = DiscountRate.noneProductRate;
                break;
        }

        return productRate;
    }
}
