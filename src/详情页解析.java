import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.util.List;

public class 详情页解析 {
    public static void main(String[] args) {

        try(WebClient webClient=new WebClient(BrowserVersion.CHROME)){
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            InputStream is=new FileInputStream("唐诗链接");
            Reader reader=new InputStreamReader(is,"utf-8");
            BufferedReader bf=new BufferedReader(reader);
            String Url;
            while((Url=bf.readLine())!=null){

                HtmlPage page=webClient.getPage(Url);
                HtmlElement element=page.getBody();
/*
                List<HtmlElement>elements= element.getElementsByAttribute("div",
                        "class",
                        "contson");
                    for(HtmlElement e:elements){
                        System.out.println(e);
                    }
                System.out.println(elements.get(0).getTextContent().trim());
*/
                // 标题
                {
                    String xpath="//div[@class='cont']/h1/text()";

                   Object obj=  element.getByXPath(xpath).get(0);

                    DomText domText =(DomText)obj;

                    System.out.println(domText.asText());
                }
                //朝代
                {
                    String Xpath="//div[@class='cont']/p[@class='source']/a[1]/text()";
                    Object obj=element.getByXPath(Xpath).get(0);
                    DomText domText=(DomText)obj;

                    System.out.println(domText.asText());
                }

                //作者
                {
                    String xPath="//div[@class='cont']/p[@class='source']/a[2]/text()";
                    Object obj=element.getByXPath(xPath).get(0);
                    DomText domText=(DomText)obj;

                    System.out.println(domText.asText());
                }

                //正文
                {
                    String xPath="//div[@class='cont']/div[@class='contson']";
                    Object obj=element.getByXPath(xPath).get(0);
                    HtmlElement htmlElement=(HtmlElement) obj;

                    System.out.println(htmlElement.getTextContent().trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}