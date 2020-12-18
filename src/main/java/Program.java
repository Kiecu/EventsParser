import Aggregates.EventAggregate;
import Aggregates.EventState;
import Models.EventRawLog;

import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class Program {

    public static void main(String args[]){

        try {
            if(args.length == 0)
            {
                throw new InvalidParameterException("Missing filename.");
            }

            String fileName = args[0];
            FileService fileService = new FileService();
            FileParser fileParser = new FileParser();

            InputStream stream = fileService.ReadFile(fileName);
            ArrayList<EventRawLog> eventRawLogs =  fileParser.Parse(stream);

            EventAggregate aggregate = new EventAggregate();
            ArrayList<EventState> events = aggregate.CreateEvents(eventRawLogs);

            Arrays.asList(events).forEach(s -> System.out.println(s.toString()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}