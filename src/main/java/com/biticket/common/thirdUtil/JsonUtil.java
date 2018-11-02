package com.biticket.common.thirdUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.biticket.common.utils.ClassUtil;
import com.biticket.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 * 
 * @author
 *
 */
public class JsonUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static SerializerFeature[] serializerFeature = { SerializerFeature.PrettyFormat,
			SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNullListAsEmpty };

	
	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String[] object2Arr(List<Object> o) {
        if (o == null) {
            return null;
        }
	   JSONArray array = new JSONArray(o);
	   String [] arr = new String[o.size()];
	   for(int i=0;i<array.size();i++)
	   {
		   arr[i] = array.getJSONObject(i).toJSONString();
	   }
	   return arr;
	}
	
	
	/**
	 * 对象转JSON
	 * 
	 * @param o
	 * @return
	 */
	public static String object2String(Object o) {
        if (o == null) {
            return null;
        }

        if (ClassUtil.isBaseDataType(o.getClass())) {
            return o.toString();
        }

		return JSON.toJSONString(o, serializerFeature);
	}
	
	public static String mapToJson(Object o) {
        if (o == null) {
            return null;
        }
        return JSONObject.toJSONString(o);
	}

	/**
	 * map转换为对像
	 * 
	 * @param s
	 * @param clz
	 * @return
	 */
	public static <T> T map2Object(Map s, Class<T> clz) {
		return JSON.parseObject(object2String(s), clz);
	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> object2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
                if (!"class".equals(key)) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			logger.error("transBean2Map Error " + e);
		}
		return map;
	}

	
	
	/**
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Map<String,JSONObject> jsonArr2Map(List<String> jsonStr) {
		if (jsonStr == null || jsonStr.isEmpty()) {
			logger.error("json is null,param error!");
			return null;
		}
	
		Map<String,JSONObject> result = new HashMap<String,JSONObject>();
		jsonStr.forEach((v) -> {
			JSONObject domain = JSON.parseObject(v);
			if (domain != null) {
				result.put(domain.getString("id"),domain);
			}
		});
		return result;
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> arr2StrList(List<?> list) {
		if (list != null && !list.isEmpty()) {
			List<String> result = new ArrayList<>();
			list.stream().forEach((v) -> result.add(object2String(v)));
			return result;
		}
		logger.error("list is empty,please check args!");
		return null;
	}

	/**
	 * 转换类型
	 *
	 * @param clz
	 * @return
	 */
	public static <T> T str2Object(String jsonStr, Class<T> clz) {
		if (StringUtils.isBlank(jsonStr)) {
			logger.error("json is null,param error!");
			return null;
		}
		return (T) JSON.parseObject(jsonStr, clz);
	}

	/**
	 * 
	 * @param jsonStr
	 * @param clz
	 * @return
	 */
	public static <T> List<T> str2List(String jsonStr, Class<T> clz) {
		if (StringUtils.isBlank(jsonStr)) {
			logger.error("json is null,param error!");
			return null;
		}
		return JSON.parseArray(jsonStr, clz);
	}

}
