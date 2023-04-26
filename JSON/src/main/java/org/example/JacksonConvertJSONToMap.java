package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class JacksonConvertJSONToMap {
    // main() method start
    public static void main(String args[]) {


        ObjectMapper mapper = new ObjectMapper();

        File fileObj = new File("/home/sankalp/Downloads/FileJason.json");


        try {

            Map<String, Object> userData = mapper.readValue(
                    fileObj, new TypeReference<Map<String, Object>>() {
                    });

            System.out.println("Name : " + userData.get("name"));

            System.out.println("author : " + userData.get("author"));

            System.out.println("license : " + userData.get("license"));

            System.out.println("description : " + userData.get("description"));

            System.out.println("main : " + userData.get("main"));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
