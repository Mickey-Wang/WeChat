
package com.github.MickeyWang.po;

/**<p>Title: 票据的实体对象</p>
 * <p>Description: </p>
 * @author MickeyWang
 * @version 1.0
 * @since 2016年2月24日 下午11:27:44
 */
public class AccessToken {
	
	private String token;//获取到的凭证
	private int expiresIn;//凭证有效时间，单位：秒
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
