package com.github.MickeyWang.po;

/**<p>Title: 文本信息类</p>
 * <p>Description: 接收或发送到微信服务器文本消息的抽象</p>
 * @author MickeyWang
 * @version 1.0
 * @since 2016年2月3日 下午11:22:44
 */
public class TextMessage extends BaseMessage{
	
	private String Content ;//文本消息内容
	private String MsgId ;//消息id，64位整型
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	
}
