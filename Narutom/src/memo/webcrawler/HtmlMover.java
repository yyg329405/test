package memo.webcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

public class HtmlMover {
	public static void main(String[] args) throws Exception {
//		NodeList rt = getNodeList("http://s.taobao.com/search?spm=a230r.1.8.3.N4ZdVg&promote=0&sort=sale-desc&initiative_id=tbindexz_20140503&tab=all&q=%CA%F3%B1%EA&style=grid#J_relative");
//		System.out.println(rt.toHtml());
		
//		captureHtml("111.142.55.73");
		
//		captureJavascript("107818590577");
		
		mytest();
	}

	/**
	 * 抓去整个网页
	 */
	public static NodeList getNodeList(String url) {
		Parser parser = null;
		HtmlPage visitor = null;
		try {
			parser = new Parser(url);
			parser.setEncoding("GBK");
			visitor = new HtmlPage(parser);
			parser.visitAllNodesWith(visitor);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		NodeList nodeList = visitor.getBody();
		return nodeList;
	}
	
	/**
	 * from web get ip
	 */
	public static void captureHtml(String ip) throws Exception {
		String strURL = "http://ip.chinaz.com/?IP=" + ip;
		URL url = new URL(strURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(httpConn
				.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		String buf = contentBuf.toString();
		System.out.println("返回结果："+buf);
		int beginIx = buf.indexOf("查询结果[");
		int endIx = buf.indexOf("上面四项依次显示的是");
		String result = buf.substring(beginIx, endIx);
		System.out.println("captureHtml()的结果：\n" + result);
	}
	
	/**
	 * 抓去js返回结果
	 */
	public static void captureJavascript(String postid) throws Exception {
		String strURL = "http://www.kiees.cn/sf.php?wen=" + postid
				+ "&channel=&rnd=0";
		URL url = new URL(strURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(httpConn
				.getInputStream(), "gbk");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		System.out.println("captureJavascript()的结果：\n" + contentBuf.toString());
	}
	
	/**
	 * mytest
	 * @throws Exception 
	 */
	public static void mytest() throws Exception{
		String urlStr = "http://www.baidu.com/";
		URL url = new URL(urlStr);
		HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(httpconn.getInputStream(), "UTF8"); 
		BufferedReader bufferbuilder = new BufferedReader(input);
		
		String line = "";
		StringBuilder stringBuild = new StringBuilder();
		while((line = bufferbuilder.readLine())!=null){
			stringBuild.append(line);
		}
		System.out.println(stringBuild);
	}
	
}