package Aggregates;

import Models.EventRawLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventAggregate {
    private EventState state;

    public EventAggregate(){
    }

    public EventAggregate(EventState state){

        this.state = state;
    }

    public ArrayList<EventState> CreateEvents(ArrayList<EventRawLog> eventRawLogs)
    {
        ArrayList<EventState> eventLogs = new ArrayList<>();
        Map<String, EventRawLog> rawLogMap = new HashMap<>();

        for (EventRawLog eventRawLog : eventRawLogs) {

            if(rawLogMap.containsKey(eventRawLog.getId()))
            {
                EventState eventState = this.CreateEventLogs(rawLogMap.get(eventRawLog.getId()), eventRawLog);
                eventLogs.add(eventState);
            }
            else
            {
                rawLogMap.put(eventRawLog.getId(),eventRawLog);
            }
        }

        return eventLogs;
    }

    private EventState CreateEventLogs(EventRawLog firstEvent, EventRawLog secondEvent)
    {
        EventState eventState = new EventState();

        eventState.setId(firstEvent.getId());
        eventState.setHost(firstEvent.getHost() != null ? firstEvent.getHost() : secondEvent.getHost());
        eventState.setType(firstEvent.getType() != null ? firstEvent.getType(): secondEvent.getType());

        long duration = Math.abs(firstEvent.getTimestamp()-secondEvent.getTimestamp());
        eventState.setDuration(duration);

        eventState.setAlert(duration > 4 ? true : false);

        return eventState;
    }
}
