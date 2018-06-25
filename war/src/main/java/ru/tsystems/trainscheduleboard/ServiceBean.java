/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tsystems.trainscheduleboard;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.rabbitmq.client.*;
import ru.tsystems.trainscheduleboard.dto.*;

@ManagedBean
@ViewScoped
public class ServiceBean implements Serializable {

    @EJB (beanName = RestClientImpl.JNDI_NAME)
    RestClient restClient;

    private static boolean isResiveStart = false;
    private static ScheduleDTOList scheduleDTOList;
    private static StationDTOList stationDTOList;
    static{
        stationDTOList = requestStations();
    }

    public String favCoffee3 = "Moscow";

    public String getFavCoffee3() {
        return favCoffee3;
    }

    public void setFavCoffee3(String favCoffee3) {
        this.favCoffee3 = favCoffee3;
    }
    public Date now;

    public Date getNow() {
        return new Date();
    }

    private final static String QUEUE_NAME = "hello";
    private static String message;

    public String getList() {
        return scheduleDTOList.getScheduleDTOList().toString();
    }

    public List<ScheduleDTO> getListProduct() {
        return scheduleDTOList.getScheduleDTOList();
    }

    public String getStation() {
        return stationDTOList.getStationDTOList().toString();
    }

    public List<StationDTO> getListStation() {
        return stationDTOList.getStationDTOList();
    }

    public static StationDTOList requestStations(){

        System.out.println("constructor init. start");
        String path = "allstation";

        stationDTOList = new StationDTOList();
        JsonReader jsonReader = new JsonReader();
        try {
            stationDTOList.setStationDTOList(jsonReader.getStations(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stationDTOList;
    }

    @PostConstruct
    public void ServiceBeanInit() {

        //if (!isResiveStart)
        init();
        String path = "scheduledataWS";

        if (scheduleDTOList == null|| message!=null) {
            message = null;
            scheduleDTOList = new ScheduleDTOList();
            JsonReader jsonReader = new JsonReader();
            try {
                scheduleDTOList.setScheduleDTOList(jsonReader.getSchedule(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void getUpdate(){
        System.out.println("getUpdate start");
        String path = "scheduledataWS";

        if (message!=null) {
            System.out.println("getUpdate message!=null");
            message = null;
            scheduleDTOList = new ScheduleDTOList();

            JsonReader jsonReader = new JsonReader();
            try {
                scheduleDTOList.setScheduleDTOList(jsonReader.getSchedule(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("after setScheduleDTOList");
        }
        System.out.println("getUpdate end");
    }

    private static void init() {
        isResiveStart = true;
        System.out.println("isResiveStart " + isResiveStart);

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
                    message = new String(body, "UTF-8");
                    System.out.println(" [x] handleDelivery Received message1 '" + message+ "'");
                    ServiceBean.getUpdate();

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

    }

}
