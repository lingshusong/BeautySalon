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

    public static double getServiceRate(String customerType) {
        double serviceRate = 0.0;

        switch (customerType) {
            case "gold":
                serviceRate = DiscountRate.goldServiceRate;
                break;
            case "silver":
                serviceRate = DiscountRate.silverServiceRate;
                break;
            case "normal":
                serviceRate = DiscountRate.normalServiceRate;
                break;
            default:
                serviceRate = DiscountRate.noneServiceRate;
                break;
        }

        return serviceRate;
    }

    public static double getProductRate(String customerType) {
        double productRate = 0.0;

        switch (customerType) {
            case "gold":
                productRate = DiscountRate.goldProductRate;
                break;
            case "silver":
                productRate = DiscountRate.silverProductRate;
                break;
            case "normal":
                productRate = DiscountRate.normalProductRate;
                break;
            default:
                productRate = DiscountRate.noneProductRate;
                break;
        }

        return productRate;
    }
}
