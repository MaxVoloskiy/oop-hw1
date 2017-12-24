package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private ArrayList<Tuple<String, Integer>> arrayList = new ArrayList<>();

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.arrayList.addAll(Arrays.asList(exams));
    }

    @Override
    public JsonObject toJsonObject() {
       JsonPair name = new JsonPair("name", new JsonString(this.name));
       JsonPair surname = new JsonPair("surname", new JsonString(this.surname));
       JsonPair year = new JsonPair("year", new JsonNumber(this.year));
       JsonObject[] jsonObjects = new JsonObject[arrayList.size()];
       for (int i = 0; i < jsonObjects.length; i++){
           jsonObjects[i] = new JsonObject(
                   new JsonPair("course", new JsonString(arrayList.get(i).key)),
                   new JsonPair("mark", new JsonNumber(arrayList.get(i).value)),
                   new JsonPair("passed", new JsonBoolean(arrayList.get(i).value > 2)));
       }
       JsonPair exams = new JsonPair("exams", new JsonArray(jsonObjects));

       return new JsonObject(name, surname, year, exams);
    }
}
