package edu.temple.cis.c3238.banksim;

import java.util.concurrent.Semaphore;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 */
class TransferThread extends Thread {

    private final Bank bank;
    private final int fromAccount;
    private final int maxAmount;
    Semaphore semaphore = null;

    public TransferThread(Bank b, int from, int max, Semaphore semaphore) {
        bank = b;
        fromAccount = from;
        maxAmount = max;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
            semaphore.acquire(); 
            } catch (InterruptedException e){
                
            }
            
            int toAccount = (int) (bank.size() * Math.random());
            int amount = (int) (maxAmount * Math.random());
            bank.transfer(fromAccount, toAccount, amount);
            
            semaphore.release(); 
            
            try {
                sleep(2);
            } catch (InterruptedException e) {
                
            }
        }
        bank.close();
    }
}
