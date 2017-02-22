package com.wpc.design_patterns.abstract_factory;

import com.wpc.design_patterns.factory_method.Sender;

/**
 * 抽象工厂模式
 * 抽象工厂接口，为了解决普通工厂模式扩展功能，可以创建抽象的工厂接口，来创建不同的接口
 * 就用到抽象工厂模式，创建多个工厂类，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，不需要修改之前的代码。
 * 其实这个模式的好处就是，如果你现在想增加一个功能：发及时信息，则只需做一个实现类，实现Sender接口，同时做一个工厂类，实现Provider接口，就OK了，无需去改动现成的代码。这样做，拓展性较好！
 * @author admin
 *
 */
public interface Provider {

	public Sender produce();
}
