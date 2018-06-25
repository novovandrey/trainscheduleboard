package ru.tsystems.trainscheduleboard;

import com.rabbitmq.client.*;
//import org.apache.log4j.Logger;

import javax.faces.bean.ManagedProperty;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeoutException;

public class ReceiveMsg  {

//    private static Logger logger = Logger.getLogger(RestClientImpl.class);
    private final static String QUEUE_NAME = "hello";
//    private String message;
    private static String output;

    @ManagedProperty(value="#{serviceBean.message}")
    private String message; // +setter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReceiveMsg() {

    }

    public String init(){

//        logger.error("ReceiveMsg Starts!");
//        logger.debug("ReceiveMsg Starts!");
//        logger.fatal("ReceiveMsg Starts!");
//        logger.trace("ReceiveMsg Starts!");
//        logger.info("ReceiveMsg Starts!");
        String output = null;

        try {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    //output = new String(body, "UTF-8");
                    //System.out.println(" [x] Received '" + output + "'");

                    String URLstring = "http://localhost:8081/webService1";
                    URL url = new URL(URLstring);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                    if (conn.getResponseCode() != 200) {
                        System.out.println("Error");;
                    }
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));
                    message = br.readLine();
                    conn.disconnect();
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);

        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (TimeoutException e){
            System.out.println(e);
        }
        return message;
    }
}
