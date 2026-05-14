package javalab1;

import java.net.*;
import java.io.*;

public class ComputeClient {
    
    private static final int DEFAULT_LISTEN_PORT = 12345;
    private static final int SERVER_PORT = 12346;
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int THREAD_COUNT = 7;

    public static void main(String[] args) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                System.setOut(new PrintStream(System.out, true, "CP866"));
            } else {
                System.setOut(new PrintStream(System.out, true, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Listen port: from command-line arg or interactive prompt
        int listenPort = DEFAULT_LISTEN_PORT;
        if (args.length > 0) {
            try {
                listenPort = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port arg, falling back to default " + DEFAULT_LISTEN_PORT);
            }
        } else {
            String input = javax.swing.JOptionPane.showInputDialog(
                null,
                "Enter listen port for this ComputeClient:",
                "ComputeClient",
                javax.swing.JOptionPane.QUESTION_MESSAGE
            );
            if (input != null && !input.trim().isEmpty()) {
                try {
                    listenPort = Integer.parseInt(input.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid port input, using default " + DEFAULT_LISTEN_PORT);
                }
            }
        }
        
        System.out.println("===================================");
        System.out.println("UDP ComputeClient started on port " + listenPort);
        System.out.println("Results sent to " + SERVER_ADDRESS + ":" + SERVER_PORT);
        System.out.println("Using " + THREAD_COUNT + " threads per task");
        System.out.println("===================================");

        try (DatagramSocket socket = new DatagramSocket(listenPort)) {
            
            while (true) {
                byte[] buffer = new byte[4096];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                System.out.println("\nWaiting for task...");
                socket.receive(request);

                ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(request.getData())
                );
                RecIntegral task = (RecIntegral) ois.readObject();
                
                System.out.println("Received task ID=" + task.getId() 
                    + ": [" + task.getLowerBound() 
                    + ", " + task.getUpperBound() + "], step=" + task.getStep());

                new Thread(() -> {
                    try {
                        double result = computeParallel(task);
                        
                        RecIntegral resultRec = new RecIntegral(
                            task.getLowerBound(), 
                            task.getUpperBound(), 
                            task.getStep()
                        );
                        resultRec.setId(task.getId());
                        resultRec.setResult(String.format("%.6f", result).replace(",", "."));
                        
                        sendResult(resultRec, SERVER_ADDRESS, SERVER_PORT);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static double computeParallel(RecIntegral task) {
        double low = task.getLowerBound();
        double up = task.getUpperBound();
        double step = task.getStep();
        
        double fullInterval = up - low;
        double delta = fullInterval / THREAD_COUNT;
        
        Thread[] threads = new Thread[THREAD_COUNT];
        double[] partialResults = new double[THREAD_COUNT];
        
        long startTime = System.nanoTime();

        for (int i = 0; i < THREAD_COUNT; i++) {
            double start = low + i * delta;
            double end = (i == THREAD_COUNT - 1) ? up : start + delta;
            
            threads[i] = new Thread(new CalculationThread(start, end, step, i, partialResults));
            threads[i].start();
        }

        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        double totalSum = 0;
        for (double res : partialResults) {
            totalSum += res;
        }
        
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;
        System.out.println("Calculation done in " + durationMs + " ms, result=" + totalSum);

        return totalSum;
    }
    
    private static void sendResult(RecIntegral resultRec, String serverAddress, int serverPort) {
        try (DatagramSocket socket = new DatagramSocket()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(resultRec);
            byte[] data = baos.toByteArray();

            InetAddress address = InetAddress.getByName(serverAddress);
            DatagramPacket response = new DatagramPacket(
                data, data.length, address, serverPort
            );
            socket.send(response);
            
            System.out.println("Result sent to server: ID=" + resultRec.getId() 
                + ", value=" + resultRec.getResult());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}