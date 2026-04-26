/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalab1;

import java.net.*;
import java.io.*;

public class ComputeClient {
    public static void main(String[] args) {
        int port = 12345; 
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("UDP Client active (port " + port + "). waiting...");

            while (true) {
                byte[] buffer = new byte[4096];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(request.getData()));
                RecIntegral task = (RecIntegral) ois.readObject();
                
                System.out.println("polychena zadacha: nigniy predel " + task.getLowerBound());

                new Thread(() -> {
                    task.calculate(); 
                    try {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(baos);
                        oos.writeObject(task);
                        byte[] responseData = baos.toByteArray();

                        DatagramPacket response = new DatagramPacket(
                            responseData, responseData.length, request.getAddress(), request.getPort()
                        );
                        socket.send(response);
                        System.out.println("Result .");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
