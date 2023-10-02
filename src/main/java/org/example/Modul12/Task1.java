package org.example.Modul12;

public class Task1 extends Thread{


    public static void main(String[] args) {
       long time = System.currentTimeMillis();
    Thread thread = new Thread(() -> {
        while (true){
            try {
                Thread.sleep(5000);
                System.out.println("Минуло 5 секунд");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
            );
    thread.start();

while (true){
    long currentTime = System.currentTimeMillis();
    long finishTime = currentTime - time;
    System.out.println("Минуло " + finishTime + " секунд");
    try {
Thread.sleep(1000);
    } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
}



    }
}
