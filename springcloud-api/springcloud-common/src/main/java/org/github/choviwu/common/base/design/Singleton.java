package org.github..common.base.design;

/**
 * 单利模式
 */
public class Singleton implements Runnable{

    private volatile static Singleton singleton;

    private static boolean status = false;

    static {
        singleton = new Singleton();
    }

    public static Singleton newInstance() {
        if(singleton==null){
            singleton = new Singleton();
        }
        status = true;
        return singleton;
    }

    public static boolean getStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        Singleton.status = status;
    }

    public static void main(String[] args) {
        for (int i = 1;i<10;i++){
            new Thread(new Singleton()).start();
        }
    }

    @Override
    public void run() {
        System.out.println(newInstance().hashCode() + "    " + getStatus());
    }
}
