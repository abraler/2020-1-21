import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class 列表页下载提取 {
    public static void main(String[] args) throws IOException {
        WebClient webClient=new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        OutputStream os=new FileOutputStream("唐诗链接");

        String url="https://www.gushiwen.org/gushi/tangshi.aspx";
        HtmlPage page=webClient.getPage(url);
        HtmlElement body=page.getBody();
        List<HtmlElement> elementList=body.getElementsByAttribute("div",
                "class",
                "typecont");
        int count=0;
        for(HtmlElement element:elementList){
            List<HtmlElement>aElement=element.getElementsByTagName("a");
            for(HtmlElement a:aElement){
                //System.out.println(a.getAttribute("href"));
                os.write(a.getAttribute("href").getBytes());
                os.write("\r\n".getBytes());
                count++;
            }
        }
        os.close();
        System.out.println(count);
    }
}
