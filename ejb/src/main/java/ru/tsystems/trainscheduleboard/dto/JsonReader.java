package ru.tsystems.trainscheduleboard.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray jsonArray = new JSONArray(jsonText);
            return jsonArray;
        } finally {
            is.close();
        }
    }

    public List<ScheduleDTO> getSchedule(String path) throws IOException, JSONException {
        System.out.println("JsonReader getSchedule start");

        ObjectMapper mapper = new ObjectMapper();
        URL jsonUrl = new URL("http://localhost:8081/"+path);

        ScheduleDTO[] dtos;
        dtos = mapper.readValue(jsonUrl, ScheduleDTO[].class);
        System.out.println("JsonReader getSchedule JACKSON: "+ dtos);
        List<ScheduleDTO> list = new ArrayList<ScheduleDTO>();

        for (ScheduleDTO scheduleDTO:dtos
             ) {
            list.add(scheduleDTO);
        }

        System.out.println("JsonReader getSchedule list: " + list);
        System.out.println("JsonReader getSchedule end");
        return list;
    }

    public List<StationDTO> getStations(String path) throws IOException, JSONException {
        System.out.println("JsonReader getStations start");

        ObjectMapper mapper = new ObjectMapper();
        URL jsonUrl = new URL("http://localhost:8081/"+path);

        StationDTO[] dtos;
        dtos = mapper.readValue(jsonUrl, StationDTO[].class);
        System.out.println("JsonReader getSchedule JACKSON: "+ dtos);
        List<StationDTO> list = new ArrayList<StationDTO>();

        for (StationDTO stationDTO:dtos
                ) {
            list.add(stationDTO);
        }

        System.out.println("JsonReader getSchedule list: " + list);
        System.out.println("JsonReader getSchedule end");
        return list;
    }
}
