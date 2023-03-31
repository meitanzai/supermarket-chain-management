package com.cqupt.th.supermarket.controller;

import com.cqupt.th.supermarket.entity.Position;
import com.cqupt.th.supermarket.query.PositionQuery;
import com.cqupt.th.supermarket.service.PositionService;
import com.cqupt.th.supermarket.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("{currentPage}/{pageSize}")
    public CommonResult getPositionListPage(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, @RequestBody(required = false) PositionQuery positionQuery) {
        return positionService.getPositionListPage(currentPage, pageSize, positionQuery);
    }
    @DeleteMapping("{id}")
    public CommonResult deletePositionById(@PathVariable("id") Integer id) {
        return positionService.deletePositionById(id);
    }
    @PutMapping("{id}")
    public CommonResult updatePositionById(@PathVariable("id") Integer id, @RequestBody Position position) {
        return positionService.updatePositionById(id, position);
    }
    @DeleteMapping("batch/{ids}")
    public CommonResult deletePositionByIds(@PathVariable("ids") Integer[] ids) {
        return positionService.deletePositionByIds(ids);
    }
    @PostMapping
    public CommonResult addPosition(@RequestBody Position position) {
        return positionService.addPosition(position);
    }
}
