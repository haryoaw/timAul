package main;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import main.CrawlerController;

/**
 * Created by haryoaw on 18/02/16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "data/crawl/root";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
     //   controller.addSeed("http://www.forbes.com/sites/kevinkruse/2013/05/28/inspirational-quotes/#40bf3a846697");
        controller.addSeed("http://auliachair.tumblr.com/");
       // controller.addSeed("http://auliachair.tumblr.com/post/138592974417/belajar-mendengarkan");
       // controller.addSeed("http://auliachair.tumblr.com/post/136452530862/hal-kecil");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(CrawlerController.class, numberOfCrawlers);
    }
}
