package com.wpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MyController {

    @RequestMapping(value = "/test")
    public String test(ModelMap model) {
        return "test";
    }

    @RequestMapping(value = "/dd", method = RequestMethod.POST)
    public String a(ModelMap model) {
        return "dragDrop/index";
    }

    @RequestMapping(value = "/ti", method = RequestMethod.POST)
    public String b(ModelMap model) {
        return "my/ti";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(ModelMap model) {
        return "my/upload";
    }
}
