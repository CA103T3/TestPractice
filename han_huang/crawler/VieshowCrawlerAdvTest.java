import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class VieshowCrawlerAdvTest {

    public static void main(String[] args) {
        VieshowCrawlerAdv crawler = new VieshowCrawlerAdv();

//        if(crawler.getTheaters() == null) {
//            System.out.println("crawler.getTheaters() : null");
//        }
//        
//        try {
//            crawler.getShowTimes();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
        crawler.fetchTheaters();
        List<HashMap<String, String>> sessions;
        try {
            sessions = crawler.getShowTimes();
            StringBuilder sb = crawler.getDataSb(sessions);
            crawler.outputFile(sb.toString());
            System.out.println("output done");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
//        try {
//            crawler.crawl();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
    }
}
