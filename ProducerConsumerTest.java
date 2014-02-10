package project1;

public class ProducerConsumerTest {
    public static void main(String[] args) {
        Drop d = new Drop();
        Producer p1 = new Producer(d);
        Consumer c1 = new Consumer(d, 1);
 
        p1.start();
        c1.start();
    }
}