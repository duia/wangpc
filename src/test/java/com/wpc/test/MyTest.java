package com.wpc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTest {

	public static void main(String[] args) {
		
		/**
		 * 测试Double对比
		 */
		/*Double d = 1.000000000000000;
		Integer i = 1;
		System.out.println(d.equals(i));
		System.out.println(new BigDecimal(d).equals(i));
		System.out.println(new BigDecimal(d).equals(new BigDecimal(i)));
		System.out.println(new BigDecimal(d).compareTo(new BigDecimal(i)));*/
		
		/**
		 * 测试json转换
		 */
		/*JSONObject jObj = new JSONObject("{\"fq\":3,\"shuju\":[{\"count\":1,\"periodMoney\":\"300\",\"periodDate\":\"\"},{\"count\":2,\"periodMoney\":\"400\",\"periodDate\":\"2016-11-18\"},{\"count\":3,\"periodMoney\":\"\",\"periodDate\":\"2016-11-25\"}]}");
		System.out.println(jObj.get("shuju"));
		JSONArray jArr = new JSONArray(jObj.get("shuju").toString());
		System.out.println(jArr.length());
		for (int i=0; i<jArr.length(); i++) {
			System.out.println(jArr.get(i));
			JSONObject per = new JSONObject(jArr.get(i).toString());
			System.out.println(per.get("periodMoney"));
			System.out.println(per.get("count"));
			System.out.println(per.get("periodDate"));
		}*/
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();//sdf.parse("2016-11-29");
			now = sdf.parse(sdf.format(now));
			Date startDate = sdf.parse("2016-11-29");
			Date endDate = sdf.parse("2016-11-29");
			System.out.println(now.before(startDate) || now.after(endDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
