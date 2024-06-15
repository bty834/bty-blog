package com.bty.blog.controller;

import com.bty.blog.annotation.Admin;
import com.bty.blog.domain.PageResponse;
import com.bty.blog.domain.Response;
import com.bty.blog.entity.dto.RecordCreateDTO;
import com.bty.blog.entity.dto.RecordEditDTO;
import com.bty.blog.service.RecordService;
import com.bty.blog.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author bty
 * @date 2022/11/29
 * @since 1.8
 **/
@Api(value = "记录")
@RestController
@RequiredArgsConstructor
public class RecordController extends BaseController{

    private final RecordService recordService;

    @ApiOperation(value = "查询collectionList")
    @GetMapping("/collectionList")
    public Response getCollectionList(){
        return Response.success(recordService.selectCollectionList());
    }

    @ApiOperation(value = "查询Record,返回cid")
    @GetMapping("/recordList")
    public PageResponse getRecordList(){
        helpPage();
        PageResponse pageResponse = pageResponse(recordService.selectRecordList());
        clearPage();
        return pageResponse;
    }

    @ApiOperation(value = "根据collectionId查询Record,返回cid")
    @GetMapping("/recordList/collection/{collectionId}")
    public PageResponse getRecordListByCollectionId(@PathVariable("collectionId")Integer collectionId){
        helpPage();
        PageResponse pageResponse = pageResponse(recordService.selectRecordListByCollectionId(collectionId));
        clearPage();
        return pageResponse;
    }

    @Admin
    @ApiOperation(value = "新增Record")
    @PostMapping("/record")
    public Response uploadRecord(@RequestBody RecordCreateDTO recordCreateDTO){
        recordService.uploadRecord(recordCreateDTO);
        return Response.success();
    }


    @Admin
    @ApiOperation(value = "修改Record")
    @PutMapping("/record")
    public Response editRecord(@RequestBody RecordEditDTO recordEditDTO){
        recordService.editRecord(recordEditDTO);
        return Response.success();
    }

    @Admin
    @ApiOperation(value = "删除Record")
    @DeleteMapping("/record/{recordId}")
    public Response removeRecord(@PathVariable Integer recordId){
        recordService.deleteRecord(recordId);
        return Response.success();
    }

    @Admin
    @ApiOperation(value = "新增collection")
    @PostMapping("/collection")
    public Response createCollection( String name){
        recordService.insertCollection(name);
        return Response.success();
    }
    @Admin
    @ApiOperation(value = "修改collection")
    @PutMapping("/collection")
    public Response updateCollection( Integer id, String name){
        recordService.updateCollection(id,name);
        return Response.success();
    }

    @Admin
    @ApiOperation(value = "删除collection")
    @DeleteMapping("/collection")
    public Response cancelCollection(Integer id){
        recordService.removeCollection(id);
        return Response.success();
    }

}
