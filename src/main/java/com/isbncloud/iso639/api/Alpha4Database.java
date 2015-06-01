package com.isbncloud.iso639.api;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;

public class Alpha4Database {

    private HashMap<String, Alpha4> referenceNameCache = new HashMap<>();
    private HashMap<String, Alpha4> alpha4IdCache = new HashMap<>();

    public Alpha4Database() throws IOException, URISyntaxException {
        String xml = IOUtils.toString(Alpha4Database.class.getClassLoader().getResourceAsStream("language_list.xml"));

        XStream xstream = new XStream();
        xstream.processAnnotations(Alpha4.class);

        ArrayList<Alpha4> allAlpha4 = (ArrayList<Alpha4>) xstream.fromXML(xml);

        for (Alpha4 alpha4 : allAlpha4) {
            referenceNameCache.put(alpha4.getReferenceName().toLowerCase(), alpha4);
            alpha4IdCache.put(alpha4.getAlpha4Id(), alpha4);
        }
    }

    public Alpha4 getAlpha4ByReferenceName(String referenceName) {
        if (referenceName == null) {
            return null;
        }

        return referenceNameCache.get(referenceName.toLowerCase());
    }

    public boolean isValidAlpha4ReferenceName(String referenceName) {
        if (referenceName == null) {
            return false;
        }

        return referenceNameCache.containsKey(referenceName.toLowerCase());
    }

    public Alpha4 getAlpha4ByAlpha4Id(String alpha4Id) {
        if (alpha4Id == null) {
            return null;
        }

        return alpha4IdCache.get(alpha4Id.toLowerCase());
    }

    public boolean isValidAlpha4Id(String alpha4Id) {
        if (alpha4Id == null) {
            return false;
        }

        return alpha4IdCache.containsKey(alpha4Id.toLowerCase());
    }
}
