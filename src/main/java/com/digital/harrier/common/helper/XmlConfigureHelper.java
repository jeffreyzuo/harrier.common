package com.digital.harrier.common.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class XmlConfigureHelper {
    private static final Logger logger = Logger.getLogger(XmlConfigureHelper.class);
    public static Document getDocument(String fileName) {
        if (StringUtils.isEmpty(fileName)) fileName="config.xml";
        Document document;
        try {
            InputStream inputStream = XmlConfigureHelper.class.getClassLoader().getResourceAsStream(fileName);
            SAXReader saxReader = new SAXReader();
            return saxReader.read(inputStream);
        }catch (DocumentException de) {
            logger.error(de.getMessage() + ",cause:" + de.getCause());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

    }

    public static String getItemValue(Element e,String itemName) {
        return e.elementTextTrim(itemName);
    }

    public static int getIntItemValue(Element e,String itemName) {
        String v = e.elementTextTrim(itemName);
        if(StringUtils.isEmpty(v)) return 0;
        return Integer.valueOf(v);
    }


}
