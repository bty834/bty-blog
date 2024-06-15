package com.bty.blog.dao;

import com.bty.blog.entity.Record;
import com.bty.blog.entity.dto.RecordCreateDTO;
import com.bty.blog.entity.vo.CollectionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bty
 * @date 2022/11/29
 * @since 1.8
 **/
public interface RecordMapper {

    List<CollectionVO> selectCollectionList();

    List<Record> selectRecordList();

    List<Record> selectRecordListByCollectionId(Integer collectionId);


    Integer insertRecord(@Param("record")RecordCreateDTO recordCreateDTO);

    Integer editRecord(@Param("id")Integer id,@Param("title") String title,@Param("description") String description);

    Integer insertRecordCollection(@Param("recordId") Integer id,@Param("collectionIdList") List<Integer> colletionIdList);

    Integer deleteRecordById(Integer id);

    Integer deleteRecordCollectionByRecordId(Integer id);

    Integer selectRecordIdByTitle(String title);

    Integer insertCollection(String name);

    Integer updateCollectionName(@Param("id") Integer id,@Param("name") String name);

    Integer deleteCollection(Integer id);

    Integer selectCountRecordCollection(Integer id);
}
