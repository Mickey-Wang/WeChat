package com.github.MickeyWang.po;

/**<p>Title: 图文消息</p>
 * <p>Description: 图文消息对象</p>
 * @author MickeyWang
 * @version 1.0
 * @since 2016年2月22日 下午10:58:46
 */
public class News {
	
	public String Title;
	public String Description;
	public String PicUrl;
	public String Url;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		this.Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		this.PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		this.Url = url;
	}
	
}
