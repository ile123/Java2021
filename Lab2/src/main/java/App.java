import com.ile.voda.Sensor;
import com.ile.voda.WaterSensor;
import java.util.List;

public class App{
    public static void main(String[] strings) throws InterruptedException {
        WaterSensor water = new WaterSensor();
        List<Sensor> sensors = WaterSensor.select_sensors();
        water.start(sensors);
    }
}

//cd "C:\Program Files\Mosquitto"
//mosquitto_sub -t "#" -v
//tribas pokreniti mosquitto
//gledaj sliku na mob za kako pokrenit
//promini sve u ENGLISH MOTHERFUCKER