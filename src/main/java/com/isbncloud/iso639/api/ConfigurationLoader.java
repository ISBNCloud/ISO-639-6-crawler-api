package com.isbncloud.iso639.api;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationLoader {

    public static final String LANGUAGE_XML_LOCATION = System.getProperty("user.dir") + "/conf/language_list.xml";

    /**
     * @TODO: lots of todo here eg:
     * - refactor the loading
     * - create getAlfa4ByReferenceName(String referenceName)
     * - create getAlfa4ByAlfa4Name(String alfa4Name)
     */

    private List<String> xml;
    private ArrayList<AlphaObject> allLanguages = new ArrayList<>();

    public void execute(){
        try{
            this.xml = Files.readAllLines(Paths.get("./code_list.xml"), Charset.defaultCharset());
            System.out.println(this.xml.size());
        }catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        XStream xstream = new XStream();
        int line = 0;
        String objectXML = "";

        for (String objectXMLline : xml){
            objectXML += objectXMLline;
           if(line%5==0 && line!=0) {
               AlphaObject lol = (AlphaObject) xstream.fromXML(objectXML);
               allLanguages.add(lol);
               System.out.println(objectXML);
               System.out.println("");
               objectXML = "";
           }
            line++;
        }

        System.out.printf("LOL");
    }



}
