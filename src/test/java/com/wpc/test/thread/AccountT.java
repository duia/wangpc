/**  
 * @Title:  AccountT.java   
 * @Package com.wpc.test.thread   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpengcheng     
 * @date:   2016年12月19日 上午11:47:30   
 * @version V1.0 
 */
package com.wpc.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**   
 * @ClassName:  AccountT   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wangpengcheng 
 * @date:   2016年12月19日 上午11:47:30   
 *     
 */
public class AccountT {

	private Lock accountLock = new ReentrantLock();
	private double balance;     // 账户余额  
    
    /** 
     * 存款 
     * @param money 存入金额 
     */  
    public void deposit(double money) {  
    	accountLock.lock();
        try {
        	double newBalance = balance + money;  
        	try {  
        		Thread.sleep(10);   // 模拟此业务需要一段处理时间  
        	}  
        	catch(InterruptedException ex) {  
        		ex.printStackTrace();  
        	} 
        	balance = newBalance;  
		} catch (Exception e) {
		}
        finally {  
            accountLock.unlock();  
        }
    }  
      
    /** 
     * 获得账户余额 
     */  
    public double getBalance() {  
        return balance;  
    }
}
