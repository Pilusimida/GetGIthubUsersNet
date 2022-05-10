import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class CrawlerThread implements Runnable{
    private User user;

    public CrawlerThread(User user){
        this.user = user;
    }
    @Override
    public void run(){
        Crawl crawl = new Crawl(user);
        try {
            crawl.refreshUser();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
