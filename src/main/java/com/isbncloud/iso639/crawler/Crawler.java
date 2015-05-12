package com.isbncloud.iso639.crawler;

import com.isbncloud.iso639.api.AlphaObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Crawler {

    private static final Logger logger = LogManager.getLogger(Crawler.class);

    public ArrayList<AlphaObject> parseGeoLangForLetter(char letter) throws IOException {
        logger.info("Parsing letter: " + letter);

        Document doc = Jsoup.connect("http://www.geolang.com/iso639-6/sortAlpha4.asp")
                .data("selectA4letter", String.valueOf(letter))
                .data("viewAlpha4", "View").post();

        Elements resultAlphaIds = doc.select("table[width=750][cellspacing=1] tbody tr td:first-child");
        Elements resultAlphaParentIds = doc.select("table[width=750][cellspacing=1] tbody tr td:nth-child(2)");
        Elements resultReferenceNames = doc.select("table[width=750][cellspacing=1] tbody tr td:nth-child(3)");

        ArrayList<AlphaObject> languages = new ArrayList<>();

        int Count = 0;
        for (Element resultAlphaId : resultAlphaIds) {
            if (0 < Count++) {
                Element resultAlphaParentId = resultAlphaParentIds.get(Count - 1);
                Element resultReferenceName = resultReferenceNames.get(Count - 1);

                String alphaId = resultAlphaId.text();
                String alphaParentId = resultAlphaParentId.text();
                String referenceName = resultReferenceName.text();

                languages.add(new AlphaObject(alphaId, alphaParentId, referenceName));
            }
        }

        return languages;
    }
}
