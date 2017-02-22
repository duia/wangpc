package com.wpc.design_patterns.decorator;

/**
 * 装饰模式又名包装(Wrapper)模式。装饰模式以对客户端透明的方式扩展对象的功能，是继承关系的一个替代方案。
 * 装饰模式以对客户透明的方式动态地给一个对象附加上更多的责任。换言之，客户端并不会觉得对象在装饰前和装饰后有什么不同。装饰模式可以在不使用创造更多子类的情况下，将对象的功能加以扩展。
 * 
 * ●　　抽象构件(Component)角色：给出一个抽象接口，以规范准备接收附加责任的对象。
 * ●　　具体构件(ConcreteComponent)角色：定义一个将要接收附加责任的类。
 * ●　　装饰(Decorator)角色：持有一个构件(Component)对象的实例，并定义一个与抽象构件接口一致的接口。
 * ●　　具体装饰(ConcreteDecorator)角色：负责给构件对象“贴上”附加的责任。
 * 
 * @author admin
 *
 */
public class Decorator_ms {

	public static void main(String[] args) {
//		Component c = new ConcreteComponent();//装饰模式对客户端的透明性要求程序不要声明一个ConcreteComponent类型的变量，而应当声明一个Component类型的变量。
		ConcreteComponent c = new ConcreteComponent();//然而，纯粹的装饰模式很难找到。装饰模式的用意是在不改变接口的前提下，增强所考虑的类的性能。在增强性能的时候，往往需要建立新的公开的方法。
													//这就导致了大多数的装饰模式的实现都是“半透明”的，而不是完全透明的。换言之，允许装饰模式改变接口，增加新的方法。
													//半透明的装饰模式是介于装饰模式和适配器模式之间的。适配器模式的用意是改变所考虑的类的接口，也可以通过改写一个或几个方法，或增加新的方法来增强或改变所考虑的类的功能。大多数的装饰模式实际上是半透明的装饰模式，这样的装饰模式也称做半装饰、半适配器模式。
		Decorator d = new ConcreteDecoratorB(new ConcreteDecoratorA(c));
		d.sampleOperation();
		c.addOperation();
	}
}

interface Component {
    
    public void sampleOperation();
    
}

class ConcreteComponent implements Component {

    @Override
    public void sampleOperation() {
        // 写相关的业务代码
    	System.out.println("我的方法...");
    }
    
    /**
     * 新增方法
     */
    public void addOperation(){
    	System.out.println("这是新增的拓展方法...");
    }

}

class Decorator implements Component{
    
	private Component component;
    
    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void sampleOperation() {
        // 委派给构件
        component.sampleOperation();
    }
    
}

class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }
    
    @Override
    public void sampleOperation() {
    	super.sampleOperation();
        // 写相关的业务代码
    	System.out.println("修饰方法A...");
    }
    
}

class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }
    
    @Override
    public void sampleOperation() {
    	super.sampleOperation();
        // 写相关的业务代码
    	System.out.println("修饰方法B...");
    }
}


