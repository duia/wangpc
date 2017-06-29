package com.wpc.design_patterns.template;

public class Title {
}

/**
 * 抽象的试题，对试题做一下规范
 */
abstract class AbstractTitle {

    public String title = "";

    protected abstract String setProperties();

}
