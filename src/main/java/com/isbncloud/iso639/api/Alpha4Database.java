package com.isbncloud.iso639.api;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Alpha4Database {

    public static final String LANGUAGE_XML_LOCATION = System.getProperty("user.dir") + "/conf/language_list.xml";

    private HashMap<String, Alpha4> referenceNameCache = new HashMap<>();
    private HashMap<String, Alpha4> alpha4IdCache = new HashMap<>();

    private Alpha4Database() throws IOException {
        String xml = String.join("\n", Files.readAllLines(Paths.get(LANGUAGE_XML_LOCATION)));

        XStream xstream = new XStream();

        ArrayList<Alpha4> allAlpha4 = (ArrayList<Alpha4>) xstream.fromXML(xml);

        for (Alpha4 alpha4 : allAlpha4) {
            referenceNameCache.put(alpha4.getReferenceName(), alpha4);
            alpha4IdCache.put(alpha4.getAlpha4Id(), alpha4);
        }
    }

    public Alpha4 getAlpha4ByReferenceName(String alpha4ReferenceName) {
        return referenceNameCache.get(alpha4ReferenceName);
    }

    public Alpha4 getAlpha4ByAlpha4Id(String alpha4Id) {
        return alpha4IdCache.get(alpha4Id);
    }
}
