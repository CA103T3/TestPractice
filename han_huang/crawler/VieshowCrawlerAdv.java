import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VieshowCrawlerAdv {
    private String urlCinema = "http://www.vscinemas.com.tw/ShowTimes/";
    private String urlGetGimes = "http://www.vscinemas.com.tw/ShowTimes//ShowTimes/GetShowTimes/";
    private String output = "TheatersInfo.txt";
    private List<HashMap<String, String>> theaters;
    
    public VieshowCrawlerAdv() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void setUrl(String str) {
        urlCinema = str;
    }
    
    public String getUrl() {
        return urlCinema;
    }
    
    public void setUrlGetGimes(String str) {
        urlGetGimes = str;
    }
    
    public String getUrlGetGimes() {
        return urlGetGimes;
    }
    
    public void setOutput(String str) {
        output = str;
    }
    
    public String getOutput() {
        return output;
    }
    
    public void fetchTheaters() {
        try {
            theaters = crawlCinema();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List<HashMap<String, String>> getTheaters() {
        return theaters;
    }
    
    private List<HashMap<String, String>> crawlCinema() throws IOException {
        //get cinema info
        Document doc = Jsoup.connect(urlCinema).get();
        Elements cinemas = doc.select("#CinemaNameTWInfoF option");
        List<HashMap<String, String>> theaters = new ArrayList<HashMap<String, String>>();
        //index 0 - 請選擇查詢影城, do not put it in ArrayList
        for(int i = 1; i < cinemas.size(); i++) {
            HashMap<String, String> infoMap = new HashMap<String, String>();
            infoMap.put("CinemaCode", cinemas.get(i).attr("value"));
            infoMap.put("CinemaName", cinemas.get(i).text());
            theaters.add(infoMap);
        }
        return theaters;
    }
    
    public List<HashMap<String, String>> getShowTimes() throws Exception {
        if(theaters == null) {
            throw new Exception("Not execute fetchTheaters() yet!");
        }
        
        List<HashMap<String, String>> sessions = new ArrayList<HashMap<String, String>>();
        
        for(HashMap<String, String> t : theaters) {
            try {
                getTimesInfo(t, sessions);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return sessions;
    }
    
    public void crawl() throws IOException {
        //get cinema info
        Document doc = Jsoup.connect(urlCinema).get();
        Elements cinemas = doc.select("#CinemaNameTWInfoF option");
        List<HashMap<String, String>> theaters = new ArrayList<HashMap<String, String>>();
        //index 0 - 請選擇查詢影城, do not put it in ArrayList
        for(int i = 1; i < cinemas.size(); i++) {
            HashMap<String, String> infoMap = new HashMap<String, String>();
            infoMap.put("CinemaCode", cinemas.get(i).attr("value"));
            infoMap.put("CinemaName", cinemas.get(i).text());
            theaters.add(infoMap);
        }
        
        List<HashMap<String, String>> sessions = new ArrayList<HashMap<String, String>>();
        for(HashMap<String, String> t : theaters) {
            getTimesInfo(t, sessions);
        }
        printAll(sessions);
        StringBuilder sb = getDataSb(sessions);
        outputFile(sb.toString());
    }
    
    public void printAll(List<HashMap<String, String>> sessions) {
        //StringBuilder sb = new StringBuilder();
        for(HashMap<String, String> r : sessions) {
            for(Map.Entry<String, String> e : r.entrySet()) {
//                System.out.printf("%s %s\t", e.getKey(), e.getValue());
                System.out.printf("%s\t", e.getValue());
                String output = String.format("%s\t", e.getValue());
                //sb.append(output);
            }
            //sb.append("\n");
            System.out.println();
        }
    }
    
    public StringBuilder getDataSb(List<HashMap<String, String>> sessions) {
        StringBuilder sb = new StringBuilder();
        for(HashMap<String, String> r : sessions) {
            for(Map.Entry<String, String> e : r.entrySet()) {
                String output = String.format("%s\t", e.getValue());
                sb.append(output);
            }
            sb.append("\n");
        }
        return sb;
    }
    
    public void outputFile(String str) {
        File out = new File(output);
        FileWriter fw;
        try {
            fw = new FileWriter(out);
            fw.write(str);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void getTimesInfo(HashMap<String, String> theater, List<HashMap<String, String>> sessions) throws IOException {
        //get session time
        Document doc_t = Jsoup.connect(urlGetGimes).data("CinemaCode", theater.get("CinemaCode").toString()).post();
        Elements sessionsDiv = doc_t.select("body > div[class='col-xs-12']");

        HashMap<String, String> mapTemp = new HashMap<String, String>();
        mapTemp.put("CinemaCode", theater.get("CinemaCode").toString());
        mapTemp.put("CinemaName", theater.get("CinemaName").toString());
        for(Element s : sessionsDiv) {
            String movieNameTW = s.selectFirst("strong[class~=LangTW]").text();
            String movieNameEN = s.selectFirst("strong[class~=LangEN]").text();
            mapTemp.put("MovieNameTW", movieNameTW);
            mapTemp.put("MovieNameEN", movieNameEN);
            Element showingDays = s.selectFirst("div[class='col-xs-12'] > div[class='col-xs-12']");
            Elements days = showingDays.select("strong[class='col-xs-12 LangEN RealShowDate']");

            Elements timeInfos = showingDays.select("div[class='col-xs-12 SessionTimeInfo']");
            for(int j = 0; j < days.size(); j++) {             
                String day = days.get(j).text();
                for(Element t : timeInfos.get(j).select("div[class='col-xs-0']")) {
                    //clone
                    HashMap<String, String> mapRow = cloneHashMap(mapTemp);
                    //add other info
                    mapRow.put("ShowDate", day);
                    mapRow.put("time", t.text());
                    sessions.add(mapRow);
                }
            }
        }
    }

    public HashMap<String, String> cloneHashMap(HashMap<String, String> origin) {
        HashMap<String, String> clone = new HashMap<>();
        for(Map.Entry<String, String> entry : origin.entrySet()) {
            clone.put(entry.getKey(), entry.getValue());
        }
        return clone;
    }
}
