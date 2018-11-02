package com.biticket.common.utils;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author Administrator
 *
 */
public class ClassUtil extends ClassUtils{
	
	private static Logger logger =LoggerFactory.getLogger(ClassUtil.class);
	
	/**
	 * 
	 * 
	 * @param cls
	 * @return
	 */
	public static <T> T newInstance(Class<?>cls)
	{
		try {
			return (T)cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {

			return null;
		}
	}
	
	/**
	 * 获取对象get方法
	 * @param clz
	 * @param columnName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Method getterMethod(Class clz, String columnName)
			throws NoSuchMethodException, SecurityException {
		Assert.isTrue(StringUtils.hasValue(columnName), "字段名称不能为空");
		String s = "get" + columnName.substring(0, 1).toUpperCase();
		s += columnName.substring(1);

		return clz.getMethod(s, null);

	}
	/**
	 * 获取对象方法
	 * @param clz
	 * @param interfaceMethod
	 * @param parameterTypes
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Method findBy(Class clz, String interfaceMethod,
			Class... parameterTypes)
			throws NoSuchMethodException, SecurityException {
		Assert.isTrue(StringUtils.hasValue(interfaceMethod), "方法名称不能为空");
		return clz.getMethod(interfaceMethod, parameterTypes);
	}
	/**
	 * 根据get方法获取字段名称
	 * @param methodName
	 * @return
	 */
	public static String getterFieldName(String methodName) {
		String paramName = null;
		if (StringUtils.hasValue(methodName)) {
			paramName = methodName.substring(3);

			String start = paramName.substring(0, 1).toLowerCase();

			paramName = start + paramName.substring(1);

			return paramName;
		}
		return null;
	}
	/**
	 * 获取对象set方法
	 * @param clz
	 * @param columnName
	 * @param paramClz
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Method setterMethod(Class clz, String columnName,
			Class paramClz) throws NoSuchMethodException, SecurityException {
		Assert.isTrue(StringUtils.hasValue(columnName), "字段名称不能为空");
		String s = "set" + columnName.substring(0, 1).toUpperCase();
		s += columnName.substring(1);

		Method[] methods = clz.getMethods();

		for (Method m : methods) {
            if (m.getName().equals(s)) {
                return m;
            }
		}

		return null;
	}
	/**
	 * 判断类是否为基础类型
	 * @param clz
	 * @return
	 */
	public static boolean isBaseDataType(Class clz) {
		return (clz.equals(String.class) || clz.equals(Integer.class)
				|| clz.equals(Byte.class) || clz.equals(Long.class)
				|| clz.equals(Double.class) || clz.equals(Float.class)
				|| clz.equals(Character.class) || clz.equals(Short.class)
				|| clz.equals(BigDecimal.class) || clz.equals(BigInteger.class)
				|| clz.equals(Boolean.class) || clz.equals(Date.class)
				|| clz.isPrimitive());
	}
}
