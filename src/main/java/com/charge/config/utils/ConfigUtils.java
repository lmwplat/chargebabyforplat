package com.charge.config.utils;


import com.charge.config.state.AppConstants;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * @author 
 * 
 */
public class ConfigUtils {

	private static final ResourceBundle bundle = ResourceBundle.getBundle(AppConstants.CONFIG_FILE_PATH);

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}

	/**
	 * 通过键获取值
	 * 
	 * @param key
	 * @return
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}

}
