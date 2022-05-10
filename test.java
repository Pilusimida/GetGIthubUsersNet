import net.sf.json.JSON;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
    public static List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        //inline will store the JSON data streamed in string format
        String inline = "";
        try {
            URL url = new URL("https://api.github.com/users");
            //Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Set the request to GET or POST as per the requirements
            conn.setRequestMethod("GET");
            //Use the connect method to create the connection bridge
            conn.connect();
            //Get the response status of the Rest API
            int responsecode = conn.getResponseCode();
            System.out.println("Response code is: " + responsecode);

            //Iterating condition to if response code is not 200 then throw a runtime exception
            //else continue the actual process of getting the JSON data
            if (responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            else {
                //Scanner functionality will read the JSON data from the stream
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                System.out.println("\nJSON Response in String format");

                sc.close();
            }


            JSONParser jsonParser = new JSONParser();
            JSONArray users_list = (JSONArray) jsonParser.parse(inline);
            ArrayList<JSONObject> users = new ArrayList<>();
            for (int i = 0; i < users_list.size(); i++) {
                users.add((JSONObject) users_list.get(i));
            }

            for (int i = 0; i < users.size(); i++) {
                User user = new User((String) users.get(i).get("login"));
                userList.add(user);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}

