package main;

import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.extractors.DefaultExtractor;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by haryoaw on 18/02/16.
 */
public class CrawlerController extends WebCrawler {
    static int count = 1;

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith("http://auliachair.tumblr.com/");

    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */

    @Override
    public void visit(Page page)  {
        String url = page.getWebURL().getURL();

        if (page.getParseData() instanceof HtmlParseData) {
            try {

                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                String text = htmlParseData.getText();
                String html = htmlParseData.getHtml();

                File file = new File("KumpulanQuoteAsalAul.csv");
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file,true);

                try {
                    //System.out.println(html);
                    System.out.println(url);
                    String hehe = ArticleExtractor.INSTANCE.getText(html);
                    String hehe2 = "";
                    System.out.println(hehe);

                  //  System.out.println("--- INITIALIZING NAMED ENTITIY RECOGNITION TECHNIQUE ---");

                    System.out.println("--- INITIALIZING REGEX PATTERN, COLLECTING  ---");
                    List<String> allMatches = new ArrayList<String>();
                    //REGEX -----"---"-----
                    Matcher m = Pattern.compile("(\"|“).*(\"|”).*").matcher(hehe);
                    while (m.find()) {
                        allMatches.add(m.group());
                    }
                    System.out.println("--- DONE COLLECTING, TRANSFER TO OUTPUT FILE ---");
                    for(int i= 0; i < allMatches.size(); i++)
                    {
                        String daText = allMatches.get(i);
                        System.out.println(daText);
                        fileWriter.write(count++ + "," + daText  + "," + url + "\n");
                        //TODO Find The Author AND check another pattern of quote.
                    }
                    System.out.println("");
                  //  System.out.println(text);
                    fileWriter.close();
                }
                catch(Exception e){System.out.println("ERROR AT : " + e.getMessage());}


               // String[] go = text.split("“|”");
                /*for (int i = 1; i < go.length; i++) {
                    String isi = go[i].replaceAll("\\s+", " ").trim();
                    System.out.println(count + ". " + isi + " FROM : " + url );
                    count++;
                    i += 2;
                }*/
                //System.out.println(text);


               // Set<WebURL> links = htmlParseData.getOutgoingUrls();

                //System.out.println("Text length: " + text.length());
              //  System.out.println("Html length: " + html.length());
               // System.out.println("Number of outgoing links: " + links.size());


            }
            catch (Exception e){}
            System.out.println("SLEEPING 5 SECOND...");
            try {
                Thread.sleep(100);
            }catch(Exception e){}
        }
    }
}

