package vttp.ssf.assessment.eventmanagement.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Service
public class DatabaseService {
    @Autowired
    RedisRepository redisRepo;

    // TODO: Task 1
    List<Event> listOfEvents = new ArrayList<>();
    String pathFileName = "/Users/joanna/Desktop/myProject/SSF/vttp2023-batch4-ssf-assessment/events.json";

    public List<Event> readFile(String fileName) throws IOException {

        // to read the json file
        File file = new File(pathFileName);
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);

        StringBuilder resultStringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(isr)) {
            String line = "";
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line);

            }
        }
        String data = resultStringBuilder.toString();
        // System.out.println(data); this shows me that the file is being read

        // now to read the json objects
        JsonReader jsonReader = Json.createReader(new StringReader(data));
        JsonArray jsonArray = jsonReader.readArray();
        for (JsonValue jsonValue : jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();

            String eventName = jsonObject.getString("eventName");
            Integer eventId = jsonObject.getInt("eventId");
            Integer eventSize = jsonObject.getInt("eventSize");
            JsonNumber number = jsonObject.getJsonNumber("eventDate");
            Long eventDate = number.longValue();
            Integer participants = jsonObject.getInt("participants");

            Event event = new Event();
            event.setEventId(eventId);
            event.setEventName(eventName);
            event.setEventSize(eventSize);
            event.setEventDate(eventDate);
            event.setParticipants(participants);

            System.out.println(event);

            listOfEvents.add(event); // list of Event Objects

            // redisRepo.saveRecord(data, listOfEvents);
            // String retrieved = "retrieved";
            // redisRepo.saveRecord(retrieved, listOfEvents);

        }

       

        // // redisRepo.saveRecord(retrieved, listOfEvents);
        // for (int i = 0; i<listOfEvents.size(); i++){
        // Event newEvent = listOfEvents.get(i);
        // redisRepo.saveRecord(retrieved,newEvent);

        // // redisRepo.saveRecord(eventName, listOfEvents);
        // }

        return listOfEvents;

    }

   


    public List<Event> listFromRedis(String retrieved){
    List<Event> listOfEvent = redisRepo.retrieveEvents(retrieved);
    return listOfEvent;
    }

    // public Event eventObject(List<Event> listOfEvents){

    // Event event = new Event();
    // for(Event e: listOfEvents){
    // event.equals(e);
    // }
    // System.out.println("event object: " + event);
    // return event;

}
