package gamedemo.keven.com.gamedemo.util;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import gamedemo.keven.com.gamedemo.widget.surface.GamingInfo;

/**
 * Created by keven on 16/8/12.
 */

public class XmlManager {

    /**
     * 获取 XML解析器,从资产文件夹assets下获取
     */
    public static XmlPullParser getXmlParser(String fileName,String encode){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xml = factory.newPullParser();
            xml.setInput(GamingInfo.getGamingInfo().getActivity().getAssets().open(fileName + ".plist"),encode);
            return xml;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前标签的值
     */
    public static String getValueByCurrentTag(XmlPullParser xml){
        try {
            int eventType = xml.next();
            while (true){
                //读取标签内容状态
                if(eventType == XmlPullParser.TEXT){
                    return xml.getText().trim();
                }else if(eventType == XmlPullParser.END_DOCUMENT){
                    break;
                }
                eventType = xml.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static  boolean gotoTagByTagName(XmlPullParser xml,String tagName){
        try {
            int eventType = xml.next();
            String key = null;
            while (true){
                if (eventType == XmlPullParser.START_TAG){
                    key = xml.getName();
                    if(key.trim().equals(tagName)){
                        return true;
                    }
                }else if(eventType == XmlPullParser.END_DOCUMENT){
                    return false;
                }else if(eventType == XmlPullParser.END_TAG){
                    break;
                }
                eventType = xml.next();
            }
        }catch (Exception e){

        }
    }
}
