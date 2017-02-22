package com.wpc.design_patterns.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 原型模式是一种创建型设计模式,它通过复制一个已经存在的实例来返回新的实例,而不是新建实例.被复制的实例就是我们所称的原型,这个原型是可定制的.
 * 原型模式多用于创建复杂的或者耗时的实例, 因为这种情况下,复制一个已经存在的实例可以使程序运行更高效,或者创建值相等,只是命名不一样的同类数据.
 * 
 * 原型模式中的拷贝分为"浅拷贝"和"深拷贝":
 * 浅拷贝: 对值类型的成员变量进行值的复制,对引用类型的成员变量只复制引用,不复制引用的对象.
 * 深拷贝: 对值类型的成员变量进行值的复制,对引用类型的成员变量也进行引用对象的复制.
 * @author admin
 *
 */
public class Prototype implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6409403444161752222L;
	
	private String name;//值类型的成员变量
	private SerializableObject obj;//引用类型的成员变量
	
	/*
	 * 浅复制
	 */
	public Object clone() {
		// TODO Auto-generated method stub
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/* 
	 * 深复制
	 * 利用串行化来做深复制
	 * 把对象写道流里的过程是串行化(Serilization)过程;把对象从流中读出来是并行化(Deserialization)过程. 写在流里的是对象的一个拷贝,然后再从流里读出来重建对象.
	 * 
	 */
	public Object deepClone() {  
	
		try {
			/* 写入当前对象的二进制流 */  
			ByteArrayOutputStream bos = new ByteArrayOutputStream();  
			ObjectOutputStream oos = new ObjectOutputStream(bos);  
			oos.writeObject(this);  
			
			/* 读出二进制流产生的新对象 */  
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
			ObjectInputStream ois = new ObjectInputStream(bis);  
			return ois.readObject();  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SerializableObject getObj() {
		return obj;
	}

	public void setObj(SerializableObject obj) {
		this.obj = obj;
	}

	public static void main(String[] args) {
		SerializableObject so = new SerializableObject();
		so.setName("wpc");
		Prototype p = new Prototype();
		p.setName("123");
		p.setObj(so);
		
		//浅复制
//		Prototype copyP = (Prototype) p.clone();
//		System.out.println(copyP.getName()+"-"+copyP.getObj().getName());//123-wpc 浅复制后可以将被复制对象的属性全部复制给新对象。
//		copyP.setName("copywpc");
//		copyP.getObj().setName("cpoy123");//修改克隆后的属性值
//		System.out.println(p.getName()+"-"+p.getObj().getName());//123-cpoy123 修改克隆后的对象属性值后，原对象的值类型成员变量未改变，引用型成员变量的值改变，说明浅复制对于引用型成员变量复制的是引用,不复制引用的对象
//		System.out.println(copyP.getName()+"-"+copyP.getObj().getName());//copywpc-cpoy123
		
		//浅复制
		Prototype copyP2 = (Prototype) p.deepClone();
		System.out.println(copyP2.getName()+"-"+copyP2.getObj().getName());//123-wpc 深复制后可以将被复制对象的属性全部复制给新对象。
		copyP2.setName("copywpc2");
		copyP2.getObj().setName("cpoy123123");//修改复制后的属性值
		System.out.println(p.getName()+"-"+p.getObj().getName());//123-wpc 修改复制后的对象属性值后，原对象的值类型成员变量未改变，引用型成员变量的值也未改变（不同之处），说明深复制对于引用型成员变量复制的是对象。明白了这样的不通之处，可以在深复制clone中将涉及到的引用类型的成员变量的对象clone给这个引用。pro.obj = obj.clone()
		System.out.println(copyP2.getName()+"-"+copyP2.getObj().getName());//copywpc2-cpoy123123
		
	}
}

class SerializableObject implements Serializable {  
    private static final long serialVersionUID = 1L;
    private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}
