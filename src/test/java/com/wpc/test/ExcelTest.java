/**  
 * @Title:  ExcelTest.java   
 * @Package com.wpc.test   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpengcheng     
 * @date:   2016年12月13日 下午6:39:54   
 * @version V1.0 
 */
package com.wpc.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wpc.common.utils.excel.excel.ExcelBean;
import com.wpc.common.utils.excel.excel.SheetBean;
import com.wpc.common.utils.excel.excel.WriteExcel;

/**   
 * @ClassName:  ExcelTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wangpengcheng 
 * @date:   2016年12月13日 下午6:39:54   
 *     
 */
public class ExcelTest {

	public static void main(String[] args) throws Exception {
		
//		ExcelExportHelper eeh = new ExcelExportHelper();
		
		List<Map<String, Object>> users = new ArrayList<>();
		/*User user = null;
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.setId(i+1);
			user.setAccount("wpc"+i);
			user.setUsername(i+"wpc");
			user.setPassword("wpc123");
			user.setAge(i+3);
			user.setUpdateTime(new Date());
			users.add(user);
		}*/
		Map<String, Object> user = null;
		for (int i = 0; i < 100000; i++) {
			user = new HashMap<>();
			user.put("ID", i+1);
			user.put("账户", "wpc"+i);
			user.put("昵称", i+"wpc");
			user.put("密码", "wpc123");
			user.put("年龄", i+3);
			user.put("生日", new Date());
			users.add(user);
		}
//		String[] header = {"ID", "账户", "昵称", "密码", "年龄", "生日"};
//		String[] properties = {"id", "account", "username", "password", "age", "updateTime"};
		
//		eeh.exportExcelAndSave(header, properties, users, "用户列表", "e:\\", "用户列表");
		
		
		Map<Integer, String> headNames = new HashMap<>();
		headNames.put(0, "ID");
		headNames.put(1, "账户");
		headNames.put(2, "昵称");
		headNames.put(3, "密码");
		headNames.put(4, "年龄");
		headNames.put(5, "生日");
		
		SheetBean sb = new SheetBean();
		sb.setSheetName("用户列表");
		sb.setTitle("用户列表");
		sb.setRownum(1);
		sb.setMergeColCount(6);
		sb.setHeadNames(headNames);
		sb.setTableContents(users);
		
		List<SheetBean> sheets = new ArrayList<>();
		sheets.add(sb);
//		sb.setSheetName("用户列表2");
//		sb.setTitle("用户列表2");
//		sheets.add(sb);
		
		ExcelBean eb = new ExcelBean();
		eb.setPath("e:\\excel\\123.xlsx");
		eb.setSheets(sheets);
		
		WriteExcel we = new WriteExcel();
		we.write(eb);
		
		
	}
	
}
