package com.wpc.design_patterns.strategy;

public class Strategy {
}

/**
 * 功能描述: 生成试卷的策略接口
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 */
interface IStrategy {
    Paper makePaper();
}

/**
 * 功能描述: 试卷类
 */
class Paper {
    //各种属性略
}

/**
 * 功能描述: 环境类
 */
class Context {

    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    Paper doMakePaper() {
        return this.strategy.makePaper();
    }
}

/**
 * 策略一：获取并组合真题试卷
 */
class Strategy1 implements IStrategy {
    @Override
    public Paper makePaper() {
        Paper paper = new Paper();
        System.out.println("获取相关数据：真题试卷信息、题型、试题等数据。");
        System.out.println("数据整理：整理数据，生成符合客户端需要的真题试卷。");
        return paper;
    }
}

/**
 * 策略二：试题30个试题进行随机练习
 */
class Strategy2 implements IStrategy {
    @Override
    public Paper makePaper() {
        Paper paper = new Paper();
        System.out.println("获取相关数据：随机30个试题");
        System.out.println("数据整理：整理试题，生成符合客户端要求的练习试卷。");
        return paper;
    }
}

/**
 * 客户端
 */
class App {
    public static void main(String[] args) {
        Context context;
        context = new Context(new Strategy1());//利用策略一生成试卷
        Paper paper = context.doMakePaper();
        //...
        context = new Context(new Strategy2());//利用策略二生成试卷
        paper = context.doMakePaper();
        //...
    }
}
