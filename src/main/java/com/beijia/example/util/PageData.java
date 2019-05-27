package com.beijia.example.util;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 类名: PageData <br/>
 * 说明: 参数封装Map<br/>
 * 框架利用此类,封装数据库表或者其他参数为map格式 <br/>
 * 继承HashMap实现Map,重写方法<br/>
 * 修改时间: 2014年9月20日 <br/>
 * 
 * @version 1.0.1
 */
@SuppressWarnings("rawtypes")
public class PageData extends HashMap implements Map {

	/** 序列化 */
	private static final long serialVersionUID = 1L;

	/** 封装参数map */
	Map map = null;
	HttpServletRequest request;
	/**
	 * 根据请求数据创建一个 PageData .
	 * 返回name相同为数组字段
	 * @param request
	 *            请求数据
	 */
	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request, String type) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					if(!"filt".equals(type)){
						values[i] = StringUtil.filtrateString(values[i].trim());
					}
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
				if(values.length == 1){
					returnMap.put(name, value);
				}else{
					returnMap.put(name, values);
				}
				continue;
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}
	
	/**
	 * 根据请求数据创建一个 PageData .
	 * 
	 * @param request
	 *            请求数据
	 */
	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request) {
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					values[i] = StringUtil.filtrateString(values[i].trim());
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}
	/**
	 * 创建一个 PageData 实例 .
	 */
	public PageData() {
		map = new HashMap();
	}

	
	/**
	 * 获取key
	 * 
	 * @see HashMap#get(Object)
	 */
	@Override
	public Object get(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	/**
	 * 获取key的数组数据
	 * 
	 * @see HashMap#get(Object)
	 */
	public Object getArr(Object key) {
		Object obj = null;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr.length == 1 ? arr[0] : arr);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	/**
	 * 获取key值转换成字符串 <br/>
	 * 
	 * @param key
	 * @return String
	 */
	public String getString(Object key) {
		return (String)get(key);
	}

	/**
	 * 装入key和value
	 * 
	 * @see HashMap#put(Object, Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	/**
	 * 根据key删除值
	 * 
	 * @see HashMap#remove(Object)
	 */
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	/**
	 * 清空
	 * 
	 * @see HashMap#clear()
	 */
	@Override
	public void clear() {
		map.clear();
	}

	/**
	 * 对比key
	 * 
	 * @see HashMap#containsKey(Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	/**
	 * 对比value
	 * 
	 * @see HashMap#containsValue(Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	/**
	 * @see HashMap#entrySet()
	 */
	@Override
	public Set entrySet() {
		return map.entrySet();
	}

	/**
	 * @see HashMap#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * @see HashMap#keySet()
	 */
	@Override
	public Set keySet() {
		return map.keySet();
	}

	/**
	 * @see HashMap#putAll(Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void putAll(Map t) {
		map.putAll(t);
	}

	/**
	 * @see HashMap#size()
	 */
	@Override
	public int size() {
		return map.size();
	}

	/**
	 * @see HashMap#values()
	 */
	@Override
	public Collection values() {
		return map.values();
	}

}
