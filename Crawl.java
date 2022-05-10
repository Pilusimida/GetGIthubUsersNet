import net.sf.json.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Crawl {
    private User user;

    public Crawl(User user) {
        this.user = user;
    }

    public void refreshUser() throws IOException, ParseException {
        JSONObject user_info = user_info(this.user.url);
        this.user.setAvatar_url((URL) user_info.get("avatar_url"));
        this.user.setBio((String) user_info.get("bio"));
        this.user.setEmail((String) user_info.get("email"));
        this.user.setName((String) user_info.get("name"));
        this.user.setLocation((String) user_info.get("location"));
        this.user.setCompany((String) user_info.get("company"));
        this.user.setCreated_at((String) user_info.get("created_at"));
        this.user.setUpdated_at((String) user_info.get("updated_at"));
//                user = new User(
//                        (String) user_info.get("login"),
//                        (Long) user_info.get("id"),
//                        ,
//                        (URL) user_info.get("follower_url"),
//                        (URL) user_info.get("following_url"),
//                        (URL) user_info.get("organization_url"),
//                        (URL) user_info.get("repo_url"),
//                        (URL) user_info.get("starred_url"),
//                        (String) user_info.get("name"),
//                        ,
//                        (URL) user_info.get("blog"),
//                        (String) user_info.get("location"),
//                        (String) user_info.get("email"),
//                        (String) user_info.get("hireable"),
//                        ,
//                        (Long) user_info.get("followers"),
//                        (Long) user_info.get("following"),
//                        (String) user_info.get("created_at"),
          URL follower_url = (URL)user_info.get("follower_url");
          URL following_url = (URL)user_info.get("following_url");
          JSONArray followers_info = users_info(follower_url);
          JSONArray followings_info = users_info(following_url);
        for (int i = 0; i < followers_info.size(); i++) {
            JSONObject follower = (JSONObject) followers_info.get(i);
            user.followers_list.add(new User((String)follower.get("login")));
        }
        for (int i = 0; i < followings_info.size(); i++) {
            JSONObject following = (JSONObject) followings_info.get(i);
            user.followings_list.add(new User((String)following.get("login")));
        }


    }


    public static JSONObject user_info(URL url) throws IOException, ParseException {
        String inline = "";
        JSONObject user_info;
        try {

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
                sc.close();
            }


            JSONParser jsonParser = new JSONParser();
            user_info = (JSONObject) jsonParser.parse(inline);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user_info;
    }
    public static JSONArray users_info(URL url) throws IOException, ParseException {
        String inline = "";
        JSONArray user_info;
        try {

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
                sc.close();
            }


            JSONParser jsonParser = new JSONParser();
            user_info = (JSONArray) jsonParser.parse(inline);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user_info;
    }
}
