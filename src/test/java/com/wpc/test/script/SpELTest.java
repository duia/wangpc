package com.wpc.test.script;

import com.wpc.sys.model.Department;
import com.wpc.sys.model.User;
import com.wpc.util.script.AbstractScriptParser;
import com.wpc.util.script.SpringELParser;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiayu.qiu
 */
public class SpELTest extends TestCase {

    AbstractScriptParser scriptParser=new SpringELParser();

    public void testJavaScript() throws Exception {

        String keySpEL="'test_'+#args[0]+'_'+#args[1]";

        User user = new User();
        user.setLoginName("刘德华");
        user.setEmail("376205421@qq.com");
        Object[] arguments=new Object[]{"1111", "2222", user};

        String res=scriptParser.getDefinedCacheKey(keySpEL, arguments);
        System.out.println(res);
        assertEquals("test_1111_2222", res);
        // 自定义函数使用
        Boolean rv=scriptParser.getElValue("#empty(#args[0])", arguments, Boolean.class);
        assertFalse(rv);

        String val=null;
        val=scriptParser.getElValue("#hash(#args[0])", arguments, String.class);
        System.out.println(val);
        assertEquals("1111", val);

        val=scriptParser.getElValue("#hash(#args[1])", arguments, String.class);
        System.out.println(val);
        assertEquals("2222", val);

        val=scriptParser.getElValue("#hash(#args[2])", arguments, String.class);
        System.out.println(val);
        assertEquals("-1064513091_-1827818512_1668257051_408779896", val);

        val=scriptParser.getElValue("#hash(#args)", arguments, String.class);
        System.out.println(val);
        assertEquals("151762053_-543164020_-74155789_12539352", val);
    }

    public void testReturnIsMapWithHfield() throws Exception {

        String keySpEL= "'test_'+#args[0]+'_'+#args[1]+'_'+#User.loginName+'_'+#Department.id";

        User user = new User();
        user.setLoginName("刘德华");
        user.setEmail("376205421@qq.com");
        Department dep = new Department();
        dep.setAddress("大马村");
        dep.setId(123L);

        Object[] arguments=new Object[]{"1111", "2222", user, dep};
//        keySpEL="#retVal.loginName + '---' + #retVal.loginName";

        String res=scriptParser.getDefinedCacheKey(keySpEL, arguments);
        System.out.println(res);
//        assertEquals("刘德华", res);

        // 自定义函数使用
//        Boolean rv=scriptParser.getElValue("#empty(#args[0])", arguments, Boolean.class);
//        assertFalse(rv);
    }
}
