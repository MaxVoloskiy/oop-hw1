package json;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private ArrayList<JsonPair> json;

    public JsonObject(JsonPair... jsonPairs) {
        json = new ArrayList<JsonPair>();
        for (JsonPair pair: jsonPairs){
            this.add(pair);
        }
    }

    @Override
    public String toJson() {
        StringBuilder result = new StringBuilder("");

        result.append("{");

        for (int i = 0; i < this.json.size(); i++){
            if(i == this.json.size() - 1){
                result.append("'").append(this.json.get(i).key).append("': ").append(this.json.get(i).value.toJson());
            } else{
                result.append("'").append(this.json.get(i).key).append("': ").append(this.json.get(i).value.toJson()).append(", ");
            }
        }

        result.append("}");
        return String.valueOf(result);
    }

    public void add(JsonPair jsonPair) {
        boolean check = true;
        for (int i = 0; i < this.json.size(); i++){
            if (this.json.get(i).key.equals(jsonPair.key)){
                this.json.set(i, jsonPair);
                check = false;
            }
        }
        if(check){
            this.json.add(jsonPair);
        }
    }

    public Json find(String name) {
       for (JsonPair jsonPair: this.json){
           if (jsonPair.key.equals(name)){
               return jsonPair.value;
           }
       }
       return null;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();
        for (String name: names){
            for (JsonPair jsonPair: this.json){
                if (name.equals(jsonPair.key)){
                    jsonObject.add(jsonPair);
                }
            }
        }
        return jsonObject;
    }
}
