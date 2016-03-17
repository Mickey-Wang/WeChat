
package com.github.MickeyWang.test;

import net.sf.json.JSONObject;

import com.github.MickeyWang.po.AccessToken;
import com.github.MickeyWang.util.WeixinUtil;

/**<p>Title: 微信测试类</p>
 * <p>Description: </p>
 * @author MickeyWang
 * @version 1.0
 * @since 2016年2月25日 下午10:48:39
 */
public class WeixinTest {

	public static void main(String[] args) {
		
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据:" + token.getToken());
			System.out.println("有效时间:" + token.getExpiresIn());
			
			//新增素材，获取media_id
			/*String path = "C:/Users/X1 Carbon/OneDrive/Pictures/weixin.jpg";
			String mediaId = WeixinUtil.upload(path, token.getToken(), "thumb");
			System.out.println(mediaId);*/
			
			//创建菜单
			/*String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
			int result = WeixinUtil.createMenu(token.getToken(), menu);
			if(result == 0){
				System.out.println("创建菜单成功");
			}else{
				System.out.println("错误码：" + result);
			}*/
			
			//查询菜单
			/*JSONObject jsonObject = WeixinUtil.queryMenu(token.getToken());
			System.out.println(jsonObject);*/
			//result:
			/*{"menu":{"button":[{"type":"click","name":"click菜单","key":"11","sub_button":[]},
			                   {"type":"view","name":"view菜单","url":"http://wwww.baidu.com","sub_button":[]},
			                   {"name":"菜单","sub_button":[{"type":"scancode_push","name":"扫码事件","key":"31","sub_button":[]},
			                                              {"type":"location_select","name":"地理位置事件","key":"32","sub_button":[]}]}]}}*/
			
			//删除菜单
			int result = WeixinUtil.deleteMenu(token.getToken());
			if(result == 0){
				System.out.println("删除菜单成功！");
			}else {
				System.out.println("错误码是：" + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
