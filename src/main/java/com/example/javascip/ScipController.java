package com.example.javascip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScipController {
    @Autowired
    private ScipService service;

    @RequestMapping("/solve")
    public @ResponseBody String solve() {
        return service.solve();
    }
}
