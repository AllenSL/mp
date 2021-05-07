package com.asl.mp.config;

/**
 * @ClassName Test
 * @Description
 * @Author asl
 * @Date 2021/4/26 10:37
 **/
public class Test {
    // 父类线程组
    static class GroupFather extends ThreadGroup {
        public GroupFather(String name) {
            super(name);
        }
        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            System.out.println("groupFather=" + throwable.getMessage());
        }
    }

    public static void main(String[] args) {
        // 子类线程组
        GroupFather groupSon = new GroupFather("groupSon") {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                System.out.println("groupSon=" + throwable.getMessage());
            }
        };
        Thread thread1 = new Thread(groupSon, ()->{
            throw new RuntimeException("我异常了");
        });
        thread1.start();
    }
}


