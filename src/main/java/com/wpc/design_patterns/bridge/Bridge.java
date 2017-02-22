package com.wpc.design_patterns.bridge;

/**
 * 将抽象部分与实现部分分离，使它们都可以独立的变化。
 * 所谓桥接，通俗点说就是在不同的东西之间搭一个桥，让他们能够连接起来，可以相互通讯和使用。
 * 在桥接模式中的桥接是单向的，也就是只能是抽象部分的对象去使用具体实现部分的对象，而不能反过来，也就是个单向桥。
 * 只要让抽象部分拥有实现部分的接口对象，这就桥接上了，在抽象部分就可以通过这个接口来调用具体实现部分的功能。也就是说，桥接在程序上就体现成了在抽象部分拥有实现部分的接口对象，维护桥接就是维护这个关系。
 * 桥接模式的优点
 * 	分离抽象和实现部分
 * 	更好的扩展性
 * 	可动态切换实现
 * 	可减少子类的个数
 *
 */

/**
 * 实现部分的接口
 * @author admin
 *
 */
interface Implementor {
	// 实现部分需要的某些具体功能
    public void operationImpl();
}

/**
 * 具体的实现类
 * @author admin
 *
 */
class ConcreteImplementorA implements Implementor {

	@Override
	public void operationImpl() {
		// TODO Auto-generated method stub
		System.out.println("A具体实现...");
	}
}

class ConcreteImplementorB implements Implementor {

	@Override
	public void operationImpl() {
		// TODO Auto-generated method stub
		System.out.println("B具体实现...");
	}
}

/**
 * 抽象部分
 * @author admin
 *
 */
abstract class AbstractBridge {
	// 持有一个 Implementor 对象，形成聚合关系，完成桥接
    protected Implementor implementor;
    
    public AbstractBridge(Implementor implementor) {
        this.implementor = implementor;
    }
    
    // 可能需要转调实现部分的具体实现
    public void doOperation() {
        implementor.operationImpl();
    }
}

/**
 * 抽象部分的实现，继承自抽象部分类
 * 默认使用父类的方法操作，可以复写父类方法添加额外操作，也可以添加新的方法，对抽象部分进行功能扩展
 * @author admin
 *
 */
class MyBridge1 extends AbstractBridge {

	public MyBridge1(Implementor implementor) {
		super(implementor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doOperation() {
		// TODO Auto-generated method stub
		//可以添加一些额外的操作
		super.doOperation();
	}

	//新的功能的扩展
	public void otherOperation() {
        // 实现一定的功能，可能会使用具体实现部分的实现方法,
        // 但是本方法更大的可能是使用 Abstraction 中定义的方法，
        // 通过组合使用 Abstraction 中定义的方法来完成更多的功能。
    }

}

class MyBridge2 extends AbstractBridge {

	public MyBridge2(Implementor implementor) {
		super(implementor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doOperation() {
		// TODO Auto-generated method stub
		//可以添加一些额外的操作
		super.doOperation();
	}

	//新的功能的扩展
	public void otherOperation() {
        // 实现一定的功能，可能会使用具体实现部分的实现方法,
        // 但是本方法更大的可能是使用 Abstraction 中定义的方法，
        // 通过组合使用 Abstraction 中定义的方法来完成更多的功能。
    }

}

public class Bridge {
	public static void main(String[] args) {
		Implementor implementor = new ConcreteImplementorA();
		MyBridge1 myBridge1 = new MyBridge1(implementor);
		myBridge1.doOperation();
		myBridge1.otherOperation();
	}
}

