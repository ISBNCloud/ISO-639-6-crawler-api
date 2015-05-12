package com.isbncloud.iso639.crawler;

import com.isbncloud.iso639.api.AlphaObject;
import com.isbncloud.iso639.api.ConfigurationLoader;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("Started crawler!");

        Main main = new Main();
        main.DownloadElementsIntoAnXML();
    }

    public void DownloadElementsIntoAnXML() throws IOException {
        char[] alphabet = this.getCharactersInAlphabet();

        ArrayList<AlphaObject> allLanguages = new ArrayList<>();

        Crawler crawler = new Crawler();
        for (char letter : alphabet) {
            allLanguages.addAll(crawler.parseGeoLangForLetter(letter));
        }

        saveAsXml(allLanguages);
    }

    private void saveAsXml(ArrayList<AlphaObject> allLanguages) throws IOException {
        logger.info("Started saving!");

        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
        xstream.processAnnotations(AlphaObject.class);

        String xml = "";

        for (AlphaObject all_language : allLanguages) {
            xml = xml + "\n" + xstream.toXML(all_language);
        }

        File file = new File(ConfigurationLoader.LANGUAGE_XML_LOCATION);

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(xml);
        bw.close();
    }

    private char[] getCharactersInAlphabet() {
        return "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }
}
