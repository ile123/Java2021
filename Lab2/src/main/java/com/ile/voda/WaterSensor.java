package com.ile.voda;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaterSensor {
     public void start(List<Sensor> sensors) throws InterruptedException{
          int i = 0;
          String topic = "VODA";
          String broker = "tcp://localhost:1883";
          String ClientID = "KOKO";
          int qos = 2;
          while(true){
               sensors.get(i).SetRandomSize();
               String content = JSONConvert.SensorToJSON(sensors.get(i));
               MemoryPersistence persistence = new MemoryPersistence();
               try{
                    MqttClient client = new MqttClient(broker, ClientID, persistence);
                    MqttConnectOptions options = new MqttConnectOptions();
                    options.setCleanSession(true);
                    //System.out.println("Broker: \t \n" + broker);
                    client.connect(options);
                    //System.out.println("Connetcted\n");
                    //System.out.println("Messeage sent: \t \n" + content);
                    MqttMessage message = new MqttMessage(content.getBytes());
                    message.setQos(qos);
                    client.publish(topic,message);
                    //System.out.println("Meassage published\n");
                    //istrazi logere
                     i++;
                     if(i == sensors.size()){
                          Thread.sleep(10000);
                          i = 0;
                          System.out.println("Meassage published!\n");
                     }
               } catch (MqttException error){
                    System.out.println("reason " + error.getReasonCode());
                    System.out.println("msg " + error.getMessage());
                    System.out.println("loc " + error.getLocalizedMessage());
                    System.out.println("cause " + error.getCause());
                    System.out.println("except " + error);
                    error.printStackTrace();
               }
          }
     }

     public static List<Sensor> select_sensors(){
          System.out.println("Please enter the following numbers for the following sensors: \n 1 -> Temperature \n 2 -> Pressure \n 3 -> Usage \n 4-> Bigger Usage \n 5 -> Exit \n");
          int option;
          Scanner sc = new Scanner(System.in);
          List<Sensor> sensors = new ArrayList<>();
          boolean flag = false;
          while(true){
               option = sc.nextInt();
               switch (option){
                    case 1:
                         sensors.add(new Sensor("temperatura", "int16", 10, "C"));
                         System.out.println("Temperature sensor added!\n");
                         break;
                    case 2:
                         sensors.add(new Sensor("tlak", "uint16", 1000, "Bar"));
                         System.out.println("Pressure sensor added!\n");
                         break;
                    case 3:
                         sensors.add(new Sensor("potrosnja", "uint16", 0, "litra"));
                         System.out.println("Usage added!\n");
                         break;
                    case 4:
                         sensors.add(new Sensor("Veca Potrosnja", "uint16", 10, "m3"));
                         System.out.println("Bigger Usage added!\n");
                         break;
                    case 5:
                         System.out.println("Exiting options!\n");
                         flag = true;
                         break;
               default:
                    System.out.println("Wrong option!\n");
                    break;
               }
               if(flag){
                    return  sensors;
               }
          }
     }
}
