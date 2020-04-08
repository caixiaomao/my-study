package com.study.controller;


import com.study.model.RoleModel;
import com.study.result.PageParam;
import com.study.result.PageResult;
import com.study.result.ResultView;
import com.study.service.RoleService;
import com.study.utils.CreateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author wsm
 * @since 2019-07-18
 */
@Api(description = "角色控制器")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询全部", notes = "")
    @GetMapping("/authority/getAll")
    public ResultView getAll() {
        List<RoleModel> models = roleService.list();
        return ResultView.success(models);
    }

    @ApiOperation(value = "分页条件查询", notes = "提交参数：{\"pageIndex\":1,\"pageSize\":10,\"sort\":\"name-desc\",\"condition\":\"{\'name\':\'\',\'code\':\'\',\'projectId\':\'\',\'isEnabled\':\'\'}\"}")
    @PostMapping("/authority/getPage")
    public ResultView getPage(@RequestBody PageParam pageParam) {
        PageResult pageResult = roleService.getPage(pageParam);
        return ResultView.success(pageResult);
    }

    @ApiOperation(value = "根据id查询", notes = "")
    @GetMapping("/authority/getById/{id}")
    public ResultView getById(@PathVariable Long id) {
        RoleModel model = roleService.getById(id);
        return ResultView.success(model);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping("/authority_button/add")
    public ResultView add(@RequestBody RoleModel model) {
        LocalDateTime date = LocalDateTime.now();
        model.setId(CreateUtil.id());
        model.setCreateTime(date);
        model.setUpdateTime(date);
        roleService.save(model);
        return ResultView.success(model);
    }

    @ApiOperation(value = "修改", notes = "")
    @PostMapping("/authority_button/update")
    public ResultView update(@RequestBody RoleModel model) {
        LocalDateTime date = LocalDateTime.now();
        model.setUpdateTime(date);
        roleService.updateById(model);
        return ResultView.success(model);
    }

    @ApiOperation(value = "根据id删除", notes = "")
    @DeleteMapping("/authority_button/deleteById")
    public ResultView deleteById(@RequestParam Long id) {
        roleService.removeById(id);
        return ResultView.success();
    }

    @ApiOperation(value = "根据ids删除", notes = "")
    @DeleteMapping("/authority_button/deleteByIds")
    public ResultView deleteByIds(@RequestParam Long[] ids) {
        roleService.removeByIds(Arrays.asList(ids));
        return ResultView.success();
    }

}

