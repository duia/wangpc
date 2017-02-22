package com.wpc.test.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

public class ShiroTest {

    public static void main(String[] args) {
    	WildcardPermission p1 = new WildcardPermission("user:*:0");
    	WildcardPermission p2 = new WildcardPermission("user:del:2");
    	System.out.println(p1.implies((Permission) p2));
    	Set<String> part = new HashSet<>();
    	Set<String> otherPart = new HashSet<>();
    	part.add("del*");
    	otherPart.add("del");
    	System.out.println(part.contains("*"));
    	System.out.println(part.containsAll(otherPart));
//    	if (!part.contains("*") && !part.containsAll(otherPart)) {
//            return false;
//        }
	}
    
}
