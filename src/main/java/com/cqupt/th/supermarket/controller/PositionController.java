package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author th
 * @date 2023/3/31 16:13
 */
@RestController
@CrossOrigin
@RequestMapping("/position")
public class PositionController {
    @Autowired
    @Qualifier("positionService")
    private PositionService positionService;
}
