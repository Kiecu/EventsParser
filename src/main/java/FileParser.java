import Models.EventRawLog;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileParser {

    private Gson jsonParser = new Gson();

    public ArrayList<EventRawLog> Parse(InputStream stream) throws IOException {

        InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        ArrayList<EventRawLog> eventLogs = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) eventLogs.add(this.ParseFromJson(line));

        return eventLogs;
    }

    private EventRawLog ParseFromJson(String json) {
        EventRawLog eventRawLog = jsonParser.fromJson(json, EventRawLog.class);

        return eventRawLog;
    }
}
