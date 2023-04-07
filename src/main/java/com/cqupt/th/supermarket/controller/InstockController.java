package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.query.InstockQuery;
import com.cqupt.th.supermarket.service.InstockService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author th
 * @date 2023/4/7 12:49
 */
@RestController
@CrossOrigin
@RequestMapping("/instock")
public class InstockController {
    @Autowired
    @Qualifier("instockService")
    private InstockService instockService;

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getInstockListPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @RequestBody(required = false) InstockQuery instockQuery) {
        return instockService.getInstockListPage(currentPage, pageSize, instockQuery);
    }
}
