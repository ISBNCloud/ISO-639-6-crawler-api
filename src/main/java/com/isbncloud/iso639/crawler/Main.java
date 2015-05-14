package com.isbncloud.iso639.crawler;

import com.isbncloud.iso639.api.Alpha4;
import com.isbncloud.iso639.api.Alpha4Database;
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

    public static final String LANGUAGE_XML_LOCATION = System.getProperty("user.dir") + "/conf/language_list.xml";

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("Started crawler!");

        Main main = new Main();
        ArrayList<Alpha4> allLanguages = main.downloadElements();

        main.saveAsXml(allLanguages);
    }

    public ArrayList<Alpha4> downloadElements() throws IOException {
        char[] alphabet = this.getCharactersInAlphabet();

        ArrayList<Alpha4> allLanguages = new ArrayList<>();

        Crawler crawler = new Crawler();
        for (char letter : alphabet) {
            allLanguages.addAll(crawler.parseGeoLangForLetter(letter));
        }

        return allLanguages;
    }

    private void saveAsXml(ArrayList<Alpha4> allLanguages) throws IOException {
        logger.info("Started saving!");

        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
        xstream.processAnnotations(Alpha4.class);

        String xml = xstream.toXML(allLanguages);

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(LANGUAGE_XML_LOCATION)));
        bw.write(xml);
        bw.close();
    }

    private char[] getCharactersInAlphabet() {
        return "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }
}
