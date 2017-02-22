/**  
 * @Title:  Test01.java   
 * @Package com.wpc.test.thread   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpengcheng     
 * @date:   2016年12月19日 上午11:48:46   
 * @version V1.0 
 */
package com.wpc.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**   
 * @ClassName:  Test01   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wangpengcheng 
 * @date:   2016年12月19日 上午11:48:46   
 *     
 */
public class Test01 {

	/**   
	 * @Title: main   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param args void  
	 * @author: wangpengcheng      
	 * @throws   
	 */
	public static void main(String[] args) {
		AccountT account = new AccountT();  
        ExecutorService service = Executors.newFixedThreadPool(100);  
          
        for(int i = 1; i <= 100; i++) {  
            service.execute(new AddMoneyThread(account, 1));  
        }  
          
        service.shutdown();  
          
        while(!service.isTerminated()) {}  
          
        System.out.println("账户余额: " + account.getBalance());

	}

}
