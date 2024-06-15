package com.bty.blog.service.impl;

import com.bty.blog.dao.RecordMapper;
import com.bty.blog.entity.Record;
import com.bty.blog.entity.dto.RecordCreateDTO;
import com.bty.blog.entity.dto.RecordEditDTO;
import com.bty.blog.entity.vo.CollectionVO;
import com.bty.blog.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bty
 * @date 2022/11/29
 * @since 1.8
 **/
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    public static final Logger LOGGER = LoggerFactory.getLogger(RecordServiceImpl.class);


    private final RecordMapper recordMapper;

    @Override
    public List<CollectionVO> selectCollectionList() {
        return recordMapper.selectCollectionList();
    }

    @Override
    public List<Record> selectRecordList() {
        return recordMapper.selectRecordList();
    }

    @Override
    public List<Record> selectRecordListByCollectionId(Integer collectionId) {
        return recordMapper.selectRecordListByCollectionId(collectionId);
    }

    @Override
    public void uploadRecord(RecordCreateDTO recordCreateDTO) {
        if(recordCreateDTO.getCollectionIdList().size()<1){
            LOGGER.error("collectionIdList.size() should >= 1");
            throw new RuntimeException("collectionIdList.size() should >= 1");
        }
        LOGGER.info("insert record title:{}",recordCreateDTO.getTitle());
        recordMapper.insertRecord(recordCreateDTO);
        Integer recordId = recordMapper.selectRecordIdByTitle(recordCreateDTO.getTitle());
        recordMapper.deleteRecordCollectionByRecordId(recordId);
        recordMapper.insertRecordCollection(recordId, recordCreateDTO.getCollectionIdList());
    }


    @Override
    public void editRecord(RecordEditDTO recordEditDTO) {
        if(recordEditDTO.getCollectionIdList().size()<1){
            LOGGER.error("collectionIdList.size() should >= 1");
            throw new RuntimeException("collectionIdList.size() should >= 1");
        }
        LOGGER.info("edit record id:{}",recordEditDTO.getId());
        recordMapper.editRecord(recordEditDTO.getId(), recordEditDTO.getTitle(), recordEditDTO.getDescription());
        recordMapper.deleteRecordCollectionByRecordId(recordEditDTO.getId());
        recordMapper.insertRecordCollection(recordEditDTO.getId(), recordEditDTO.getCollectionIdList());
    }

    @Override
    public void deleteRecord(Integer id) {
        LOGGER.warn("delete recordId:{}",id);
        recordMapper.deleteRecordById(id);
        recordMapper.deleteRecordCollectionByRecordId(id);
    }

    @Override
    public Integer insertCollection(String name) {
        LOGGER.info("insert collection:{}",name);
        return recordMapper.insertCollection(name);
    }

    @Override
    public Integer updateCollection(Integer id, String name) {
        LOGGER.info("update collection id:{} new name:{}",id,name);

        return recordMapper.updateCollectionName(id,name);
    }

    @Override
    public Integer removeCollection(Integer id) {
        Integer count =recordMapper.selectCountRecordCollection(id);
        if(count>0){
            LOGGER.error("collection:{} has records,can't be deleted",id);
            throw new RuntimeException("this collection has records");
        }
        return recordMapper.deleteCollection(id);
    }
}
