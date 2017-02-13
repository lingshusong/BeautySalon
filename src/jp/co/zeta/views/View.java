package jp.co.zeta.views;

import jp.co.zeta.Controller;

/**
 * Created by kang on 12/02/2017.
 */
public class View {
    protected Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void show() {

    }

    public boolean checkInput() {
        boolean result = false;

        return result;
    }

    public boolean commit() {
        boolean result = true;

        return result;
    }

    public void jump(View target) {
        target.show();
    }
}
