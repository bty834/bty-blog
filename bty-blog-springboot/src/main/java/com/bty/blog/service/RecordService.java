package com.bty.blog.service;

import com.bty.blog.entity.Record;
import com.bty.blog.entity.dto.RecordCreateDTO;
import com.bty.blog.entity.dto.RecordEditDTO;
import com.bty.blog.entity.vo.CollectionVO;

import java.util.List;

/**
 * @author bty
 * @date 2022/11/29
 * @since 1.8
 **/
public interface RecordService {

    List<CollectionVO> selectCollectionList();

    List<Record> selectRecordList();

    List<Record> selectRecordListByCollectionId(Integer collectionId);

    void uploadRecord(RecordCreateDTO recordCreateDTO);

    void editRecord(RecordEditDTO recordEditDTO);

    void deleteRecord(Integer id);

    Integer insertCollection(String name);

    Integer updateCollection(Integer id,String name);

    Integer removeCollection(Integer id);
}
