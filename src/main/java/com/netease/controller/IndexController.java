package com.netease.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wanghao on 2/9/18.
 */
@Controller
@RequestMapping(value = {"/", "/index"})
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome() {
        return "index";
    }
}
