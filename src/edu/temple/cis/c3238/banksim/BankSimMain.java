package edu.temple.cis.c3238.banksim;

import java.util.concurrent.Semaphore;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 */
public class BankSimMain {

    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) {
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        Thread[] threads = new Thread[NACCOUNTS];
        Semaphore semaphore = new Semaphore(NACCOUNTS);
        // Start a thread for each account
        for (int i = 0; i < NACCOUNTS; i++) {
            threads[i] = new TransferThread(b, i, INITIAL_BALANCE, semaphore);
            threads[i].start();
        }
        
        for (int i = 0; i < NACCOUNTS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                // Ignore this
            }
        }

//        b.test();
    }
}


