import com.ile.voda.JSONConvert;
import com.ile.voda.Sensor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MqttTest {
    private String toJSONString(Sensor sensor) {
        return "{\"dataName\":" + "\"" + sensor.getDataName() + "\"" + ",\"dataType\":" + "\"" + sensor.getDataType() + "\"" + ",\"factor\":" + sensor.getFactor() + ",\"size\":" + sensor.getSize() + ",\"unit\":" + "\"" + sensor.getUnit() + "\"}";
    }

    @Test
    public void SensorToJSON() {
        Sensor sensor = new Sensor("temperature", "int16", 10, "C");
        assertEquals(toJSONString(sensor), JSONConvert.SensorToJSON(sensor));
    }

    @Test
    public void JSONtoSensor() {
        Sensor sensor = new Sensor("temperature", "int16", 10, "C");
        assertEquals(sensor.toString(), JSONConvert.JSONToSensor("{\"dataName\":" + "\"" + sensor.getDataName() + "\"" + ",\"dataType\":" + "\"" + sensor.getDataType() + "\"" + ",\"factor\":" + sensor.getFactor() + ",\"size\":" + sensor.getSize() + ",\"size\":" + "\"" + sensor.getUnit() + "\"}").toString());
    }

    @Test
    public void getRandomValueTest(){
        Sensor sensor = new Sensor("Temperature", "int16", 10, "C");
        assertInstanceOf(Integer.class,sensor.getSize());
    }
}