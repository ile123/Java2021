import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ile.voda.JSONConvert;
import com.ile.voda.Sensor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JavaConvertTest {

    @Test
    public void SensorToJSONTest() {
        String expected = "{\"data_name\":\"temperatura\",\"data_type\":\"int16\",\"factor\":10,\"size\":2721,\"unit\":\"C\"}";
        Sensor sensor = new Sensor();
        sensor.setDataName("temperatura");
        sensor.setDataType("int16");
        sensor.setFactor(10);
        sensor.setSize(2721);
        sensor.setUnit("C");
        Gson gson = new Gson();
        String string = gson.toJson(sensor);
        assertEquals(expected,string);
    }


    @Test
    public void JSONtoSensorTest() {
        ArrayList<Sensor> input = new ArrayList<>();
        input.add(new Sensor());
        input.get(0).setDataName("temperatura");
        input.get(0).setDataType("int16");
        input.get(0).setFactor(10);
        input.get(0).setSize(2721);
        input.get(0).setUnit("C");
        Gson gson = new Gson();
        String string = gson.toJson(input);
        List<Sensor> expected = new ArrayList<>();
        Type sensorType = new TypeToken<ArrayList<Sensor>>() {}.getType();
        expected = gson.fromJson(String.valueOf(input),sensorType);
        assertEquals(expected,string);
    }

}