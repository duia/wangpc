package com.wpc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping(value = "/test")
public class CompanyController {

	// 登录成功的页面
    @RequestMapping(value = "/admin/home")
//    @ResponseBody
    public String adminHomePage(){
    	int i = 5/0;
    	Subject currentUser = SecurityUtils.getSubject();
    	System.out.println("是否有“user:view:dep:123:321”的权限："+currentUser.isPermitted("user:view:dep:123:321"));
    	System.out.println("是否有“user:view:dep:123”的权限："+currentUser.isPermitted("user:view:dep:123"));
    	System.out.println("是否有“user:view:dep”的权限："+currentUser.isPermitted("user:view:dep"));
    	System.out.println("是否有“user:create”的权限："+currentUser.isPermitted("user:create"));
    	System.out.println("是否有“user:create:1”的权限："+currentUser.isPermitted("user:create:1"));
    	System.out.println("是否有“user:update:*”的权限："+currentUser.isPermitted("user:update:*"));
    	System.out.println("是否有“user:update:1”的权限："+currentUser.isPermitted("user:update:1"));
        return "admin/home";
    }

    // 只有角色为admin的才能访问
    @RequiresRoles("admin")
    @RequestMapping(value = "/admin/role")
    @ResponseBody
    public String adminWithRole(){
        return "admin/withrole";
    }

    // 只用同时具有user:view和user:create权限才能访问
    @RequiresPermissions(value={"user:view","user:create"}, logical= Logical.AND)
    @RequestMapping(value = "/admin/auth")
    @ResponseBody
    public String adminWithAuth(){
        return "admin/withauth";
    }
    
    @RequiresPermissions(value="data_dep$1_user$2", logical= Logical.AND)
    @RequestMapping(value = "/admin/dep_user")
    @ResponseBody
    public String adminWithParam(int depId, int userId){
    	System.out.println();
        return "test";
    }
    
}
