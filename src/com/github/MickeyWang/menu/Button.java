
package com.github.MickeyWang.menu;

/**<p>Title: 菜单按钮</p>
 * <p>Description: </p>
 * @author MickeyWang
 * @version 1.0
 * @since 2016年2月27日 下午11:05:52
 */
public class Button {
	
	private String type;
	private String name;
	
	private Button[] sub_button;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
	
}
