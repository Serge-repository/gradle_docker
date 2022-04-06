package gui;

import com.web.JsonReader;
import com.web.TestLogger;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;

public class JsonReaderTests extends TestLogger {

    @Test
    @WithTag("suite:sanity")
    public void readFromJson() {
        JsonReader jsonReader = new JsonReader();
        System.out.println(jsonReader.readFromJson());
    }
}