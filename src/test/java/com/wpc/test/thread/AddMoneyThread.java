/**  
 * @Title:  AddMoneyThread.java   
 * @Package com.wpc.test.thread   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpengcheng     
 * @date:   2016年12月19日 上午11:48:10   
 * @version V1.0 
 */
package com.wpc.test.thread;

/**   
 * @ClassName:  AddMoneyThread   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wangpengcheng 
 * @date:   2016年12月19日 上午11:48:10   
 *     
 */
public class AddMoneyThread implements Runnable {

	private AccountT account;    // 存入账户  
    private double money;       // 存入金额  
  
    public AddMoneyThread(AccountT account, double money) {  
        this.account = account;  
        this.money = money;  
    }  
  
    @Override  
    public void run() {  
        account.deposit(money);  
    }

}
