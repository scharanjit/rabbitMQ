package org.turvo.client.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

    //declare the queue name
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        //set up connection to the server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //declare the queue
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");
//        Declaring a queue is idempotent - it will only be created if it
//         doesn't exist already. The message content is a byte array,
//        so you can encode whatever you like there.

        channel.close();
        connection.close();
    }
}
