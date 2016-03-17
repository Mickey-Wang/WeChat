package com.github.MickeyWang.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.github.MickeyWang.po.Image;
import com.github.MickeyWang.po.ImageMessage;
import com.github.MickeyWang.po.Music;
import com.github.MickeyWang.po.MusicMessage;
import com.github.MickeyWang.po.News;
import com.github.MickeyWang.po.NewsMessage;
import com.github.MickeyWang.po.TextMessage;
import com.thoughtworks.xstream.XStream;

/**<p>Title: 接收消息</p>
 * <p>Description: 当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。</p>
 * @author MickeyWang
 * @version 1.0
 * @since 2016年2月3日 下午10:48:37
 */
public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_MUSIC = "music"; 
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "click";
	public static final String MESSAGE_VIEW = "view";
	public static final String MESSAGE_SCANCODE = "scan";
	
	/**
	 * <p>Description: 将xml数据转化为Map集合</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月3日 下午11:16:51
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		
		Map<String,String> map = new HashMap<String, String>();
		
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc= reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		
		ins.close();
		
		return map;
	}
	
	/**
	 * <p>Description: 将文本消息对象解析成为XML</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月3日 下午11:27:22
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		//将生成的xml的根节点名称改为xml，否则为类名com.github.MickeyWang.po.TextMessage
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	/**
	 * <p>Description: 将图片消息对象解析成为XML</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月3日 下午11:27:22
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		//将生成的xml的根节点名称改为xml，否则为类名com.github.MickeyWang.po.TextMessage
		xstream.alias("xml", imageMessage.getClass());
		xstream.alias("Image", new Image().getClass());
		return xstream.toXML(imageMessage);
	}
	
	/**
	 * <p>Description: 将音乐消息对象解析成为XML</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月3日 下午11:27:22
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		//将生成的xml的根节点名称改为xml，否则为类名com.github.MickeyWang.po.TextMessage
		xstream.alias("xml", musicMessage.getClass());
		//xstream.alias("Music", new Music().getClass());
		return xstream.toXML(musicMessage);
	}
	
	/**
	 * <p>Description: 封装文本信息报文</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月20日 下午3:48:22
	 * @param fromUserName 开发者的微信号
	 * @param toUserName 发送到对方的openID
	 * @param content 发送的文本内容
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setToUserName(toUserName);
		text.setFromUserName(fromUserName);
		text.setCreateTime(new Date().getTime());
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setContent(content);
		return textMessageToXml(text);
	}
	
	/**
	 * <p>Description:拼接主菜单 </p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月20日 下午3:44:12
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请按照菜单提示进行操作：\n\n");
		sb.append("1、课程介绍\n");
		sb.append("2、课程费用\n");
		sb.append("回复？调出此菜单。");
		return sb.toString();
	}
	
	/**
	 * <p>Description: 菜单1的内容</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月20日 下午3:57:44
	 */
	public static String firstMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("介绍微信开发");
		return sb.toString();
	}
	
	/**
	 * <p>Description: 菜单2的内容</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月20日 下午3:59:36
	 */
	public static String secondMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("收费15元");
		return sb.toString();
	}
	
	/**
	 * <p>Description: 将图文信息转换为xml</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月22日 下午11:22:12
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
		//将生成的xml的根节点名称改为xml，否则为类名com.github.MickeyWang.po.TextMessage
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * <p>Description: 封装图文信息xml报文</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月22日 下午11:43:19
	 */
	public static String initNewsMessage(String toUserName,String fromUserName){
		String message  = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News();
		news.setTitle("Taylor的照片");
		news.setDescription("Taylor Swift的照片合集哦！");
		news.setPicUrl("http://mickeywang.ngrok.cc/MyOfficalAcct/image/weixin.jpg");
		news.setUrl("www.baidu.com");
		
		newsList.add(news);
		
		newsMessage.setFromUserName(fromUserName);
		newsMessage.setToUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		
		message = newsMessageToXml(newsMessage);
		return message;
	}
	
	/**
	 * <p>Description: 封装图片消息xml报文</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月25日 下午11:48:05
	 */
	public static String initImageMessage(String toUserName,String fromUserName){
		String message = null;
		Image image = new Image();
		image.setMediaId("Ogyoya1WnjLA5UJdGTqy4PzIEwTnAnVC4xJli7EryirTyAatTYMttl1Yy5MLjEoZ");
		ImageMessage imageMessage = new ImageMessage();
		
		imageMessage.setFromUserName(fromUserName);
		imageMessage.setToUserName(toUserName);
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		
		message = imageMessageToXml(imageMessage);
		return message;
	}
	
	/**
	 * <p>Description: 封装音乐消息xml报文</p>
	 * @author MickeyWang
	 * @version 1.0
	 * @since 2016年2月25日 下午11:48:05
	 */
	public static String initMusicMessage(String toUserName,String fromUserName){
		String message = null;
		Music music = new Music();
		music.setThumbMediaId("_m4kPh_7hmtd3plpMKfPp0Bq0bo6CTZu8GyhpSucS3RVi_TnOq9Hw-5WXKd6d1iO");
		music.setTitle("see you again");
		music.setDescription("这是一首音乐作品！");
		music.setMusicURL("http://mickeywang.ngrok.cc/MyOfficalAcct/resource/See You Again.mp3");
		music.setHQMusicUrl("http://mickeywang.ngrok.cc/MyOfficalAcct/resource/See You Again.mp3");
		
		MusicMessage musicMessage = new MusicMessage();
		
		musicMessage.setFromUserName(fromUserName);
		musicMessage.setToUserName(toUserName);
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		
		message = musicMessageToXml(musicMessage);
		return message;
	}
}
