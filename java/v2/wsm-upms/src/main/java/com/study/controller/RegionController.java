package com.study.controller;


import com.study.model.RegionModel;
import com.study.result.PageParam;
import com.study.result.PageResult;
import com.study.result.ResultView;
import com.study.service.RegionService;
import com.study.utils.CreateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 域（如一个公司） 前端控制器
 * </p>
 *
 * @author wsm
 * @since 2019-07-16
 */
@Api(description = "域控制器")
@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "查询全部", notes = "")
    @GetMapping("/authority/getAll")
    public ResultView getAll() {
        List<RegionModel> models = regionService.selectList(null);
        return ResultView.success(models);
    }

    @ApiOperation(value = "分页条件查询", notes = "提交参数：{\"pageIndex\":1,\"pageSize\":10,\"sort\":\"name desc\",\"condition\":\"{\'name\':\'\',\'code\':\'\',\'parentId\':\'\',\'isEnable\':\'\'}\"}")
    @PostMapping("/authority/getPage")
    public ResultView getPage(@RequestBody PageParam pageParam) {
        PageResult pageResult = regionService.getPage(pageParam);
        return ResultView.success(pageResult);
    }

    @ApiOperation(value = "根据id查询", notes = "")
    @GetMapping("/authority/getById/{id}")
    public ResultView getById(@PathVariable Long id) {
        RegionModel model = regionService.selectById(id);
        return ResultView.success(model);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping("/authority_button/add")
    public ResultView add(@RequestBody RegionModel model) {
        Date date = new Date();
        model.setId(CreateUtil.id());
        model.setCreateTime(date);
        model.setUpdateTime(date);
        regionService.insert(model);
        return ResultView.success(model);
    }

    @ApiOperation(value = "修改", notes = "")
    @PostMapping("/authority_button/update")
    public ResultView update(@RequestBody RegionModel model) {
        Date date = new Date();
        model.setUpdateTime(date);
        regionService.updateById(model);
        return ResultView.success(model);
    }

    @ApiOperation(value = "根据id删除", notes = "")
    @DeleteMapping("/authority_button/deleteById")
    public ResultView deleteById(@RequestParam Long id) {
        regionService.deleteById(id);
        return ResultView.success();
    }

    @ApiOperation(value = "根据ids删除", notes = "")
    @DeleteMapping("/authority_button/deleteByIds")
    public ResultView deleteByIds(@RequestParam Long[] ids) {
        regionService.deleteBatchIds(Arrays.asList(ids));
        return ResultView.success();
    }
}

