package com.github.MickeyWang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.github.MickeyWang.po.TextMessage;
import com.github.MickeyWang.util.CheckUtil;
import com.github.MickeyWang.util.MessageUtil;

/**<p>Title: 接入微信公众平台</p>
 * <p>Description: 验证服务器地址的有效性</p>
 * @author MickeyWang
 * @version 1.0
 * @since 2016年2月2日 下午11:57:03
*/
public class WeiXinServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String signature = request.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String timestamp = request.getParameter("timestamp");//时间戳 	
		String nonce = request.getParameter("nonce");//随机数 
		String echostr = request.getParameter("echostr");//随机字符串
		
		PrintWriter out = response.getWriter();
		//若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter out = resp.getWriter();
		try {
			Map<String,String> map = MessageUtil.xmlToMap(req);
			String toUserName  = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			//String createTime = map.get("CreateTime");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			//String msgId = map.get("MsgId");
			
			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				if("1".equals(content)){
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.firstMenu());
				}else if("2".equals(content)){     
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.secondMenu());
				}else if("?".equals(content) || "？".equals(content)){     
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
				}else if("3".equals(content)){     
					message = MessageUtil.initNewsMessage(fromUserName, toUserName);
				}else if("4".equals(content)){     
					message = MessageUtil.initImageMessage(fromUserName, toUserName);
				}else if("5".equals(content)){     
					message = MessageUtil.initMusicMessage(fromUserName, toUserName);
				}else {
					message = MessageUtil.initText(fromUserName, toUserName, "对不起，您输入的信息有误");
				}
			}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType = map.get("Event");
				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					message = MessageUtil.initText(fromUserName, toUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
					String url = map.get("EventKey");
					message = MessageUtil.initText(fromUserName, toUserName, url);
				}else if(MessageUtil.MESSAGE_LOCATION.equals(eventType)){
					String key = map.get("EventKey");
					message = MessageUtil.initText(fromUserName, toUserName, key);
				}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
					String key = map.get("EventKey");
					message = MessageUtil.initText(fromUserName, toUserName, key);
				}
			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				//弹出地理位置选择器的事件推送报文：
				//{MsgId=6256006542933693283, FromUserName=oR0OMwa2BShZtYBniILbigitaCYo, 
				//CreateTime=1456590030, Label=北京市海淀区西二旗领秀新硅谷B区(西二旗大街北), 
				//Scale=15, Location_X=40.053497, ToUserName=gh_8b696c0b4bd5, 
				//Location_Y=116.312762, MsgType=location}
				String label = map.get("Label");//地理位置的字符串信息 
				message = MessageUtil.initText(fromUserName, toUserName, label);
			}
			System.out.println(message);
			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
