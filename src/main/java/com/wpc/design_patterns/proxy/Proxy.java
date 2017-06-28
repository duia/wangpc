package com.wpc.design_patterns.proxy;

/**
 * 代理模式是对象的结构模式。代理模式给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用。
 * 所谓代理，就是一个人或者机构代表另一个人或者机构采取行动。在一些情况下，一个客户不想或者不能够直接引用一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用。
 * 
 * ●　　抽象对象角色：声明了目标对象和代理对象的共同接口，这样一来在任何可以使用目标对象的地方都可以使用代理对象。
 * ●　　目标对象角色：定义了代理对象所代表的目标对象。
 * ●　　代理对象角色：代理对象内部含有目标对象的引用，从而可以在任何时候操作目标对象；代理对象提供一个与目标对象相同的接口，以便可以在任何时候替代目标对象。代理对象通常在客户端调用传递给目标对象之前或之后，执行某个操作，而不是单纯地将调用传递给目标对象。
 * 
 * @author admin
 *
 */
public class Proxy {

	public static void main(String[] args) throws Exception {
//        AbstractObject obj1 = new RealObject(new RealObject(), "wpc");
//        obj1.operation();
//		AbstractObject obj2 = new ProxyObject("wpc");
//        obj2.operation();
        AbstractObject obj3 = new RealObject("wpc");
        AbstractObject proxy = new ProxyObject(obj3);
//        obj3.operation();
//        AbstractObject proxy = obj3.getProxy();
        proxy.operation();
	}

}

abstract class AbstractObject {
    //操作
    public abstract void operation();
    //获取代理
    public abstract AbstractObject getProxy();
}

class RealObject extends AbstractObject {

    private String name;
    //我的代理
    private AbstractObject proxy;

    public RealObject(String name) {
        this.name = name;
    }

    /*public RealObject(AbstractObject object, String name) throws Exception {
        if (object == null ) {
            throw new Exception("不能创建真实对象");
        } else {
            this.name = name;
        }
    }*/

    public AbstractObject getProxy() {
        this.proxy = new ProxyObject(this);
        return this.proxy;
    }

    private boolean isMyProxy() {
        if (this.proxy == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void operation() {
        if (isMyProxy()) {
            //一些操作
            System.out.println("一些操作");
        } else {
            System.out.println("请使用自己的代理");
        }
    }
}

class ProxyObject extends AbstractObject{
	
	AbstractObject object;

    public ProxyObject(AbstractObject object) {
        super();
        this.object = object;
    }

    /*public ProxyObject(String name) {
		super();
        try {
            object = new RealObject(this, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public AbstractObject getProxy() {
        return this;
    }

	@Override
    public void operation() {
        //调用目标对象之前可以做相关操作
        System.out.println("before");        
        object.operation();        
        //调用目标对象之后可以做相关操作
        System.out.println("after");
    }
}