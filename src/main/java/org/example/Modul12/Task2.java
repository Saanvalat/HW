package org.example.Modul12;

public class Task2 {

    int counter = 0;
    int value;
    public Task2(int value) {
        this.value = value;
    }





    public static void main(String[] args) {
    Task2 task = new Task2(8);
        Thread thread1 = new Thread(() -> {
          while (task.counter <= task.value){
              try {
                  task.fizz();
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
        });
        Thread thread2 = new Thread(() -> {
            while (task.counter <= task.value){
                try {
                    task.buzz();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread3 = new Thread(() -> {
            while (task.counter <= task.value){
                try {
                    task.fizzBuzz();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread4 = new Thread(() -> {
            while (task.counter <= task.value){
                try {
                    task.number();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    public synchronized void fizz() throws InterruptedException {
        while (counter < value){
            if (counter % 3 == 0){
                System.out.println("fizz");
                counter++;
                notifyAll();
            } else {
                wait();
            }

        }
    }

    public synchronized void buzz() throws InterruptedException {
        while (counter < value){
            if (counter % 5 == 0){
                System.out.println("buzz");
                counter++;
                notifyAll();
            } else {
                wait();
            }

        }
    }

    public synchronized void fizzBuzz() throws InterruptedException {
        while (counter < value){
            if (counter % 3 == 0 && counter % 5 == 0){
                System.out.println("FizzBuzz");
                counter++;
                notifyAll();
            } else {
                wait();
            }

        }
    }

    public synchronized void number() throws InterruptedException {
        while (counter < value){
            if (counter % 3 != 0 && counter % 5 != 0){
                System.out.println(counter);
                counter++;
                notifyAll();
            } else {
                wait();
            }

        }
    }


}
