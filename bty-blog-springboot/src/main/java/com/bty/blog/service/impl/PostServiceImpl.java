package com.bty.blog.service.impl;

import com.bty.blog.dao.PostMapper;
import com.bty.blog.entity.dto.CommentDTO;
import com.bty.blog.entity.dto.PostCreateDTO;
import com.bty.blog.entity.dto.PostEditDTO;
import com.bty.blog.entity.vo.*;
import com.bty.blog.service.PostService;
import com.bty.blog.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostMapper postMapper;

    @Override
    public List<PostCard> selectPostCardList() {
        List<PostCard> postCards = postMapper.selectPostCardList();

        for (PostCard postCard : postCards) {
            Integer postId = postCard.getId();
            List<TagVO> tagList = selectTagListByPostId(postId);
            postCard.setTagList(tagList);
        }
        return postCards;
    }

    @Override
    public PostDetail selectPostDetailById(Integer id) {
        return postMapper.selectPostDetailById(id);
    }

    @Override
    public List<TagVO> selectTagListByPostId(Integer postId) {
        return postMapper.selectTagListByPostId(postId);
    }

    @Override
    public List<PostVO> selectRecentPostList(Integer limit) {
        return postMapper.selectRecentPostList(limit);
    }

    @Override
    public List<Integer> selectPostIdList() {
        return postMapper.selectPostIdList();
    }

    @Override
    public List<PostCard> selectPostCardListByTagId(Integer tagId) {
        List<PostCard> postCards = postMapper.selectPostCardListByTagId(tagId);
        for (PostCard postCard : postCards) {
            Integer postId = postCard.getId();
            List<TagVO> tagList = selectTagListByPostId(postId);
            postCard.setTagList(tagList);
        }
        return postCards;
    }

    @Override
    public List<TagVO> selectTagList() {
        return postMapper.selectTagList();
    }

    @Override
    public List<Integer> selectTagIdList() {
        return postMapper.selectTagIdList();
    }

    @Override
    public List<CommentVO> selectCommentListByPostId(Integer postId) {
        return postMapper.selectCommentByPostId(postId);
    }

    @Override
    public void insertComment(CommentDTO comment) {
        LOGGER.info("submit comment:{}",comment);
        postMapper.insertComment(comment);
    }

    @Override
    public Integer insertPostTagList(Integer postId, List<Integer> tagIdList) {
        if(tagIdList.size()<1){
            LOGGER.error("tagIdList.size <1 for postId:{}",postId);
            throw new RuntimeException("tagIdList.size should >=1");
        }
        LOGGER.info("insert tagId {} for postId:{}",tagIdList,postId);
        return postMapper.insertPostTag(postId, tagIdList);

    }

    @Override
    public void editPost(PostEditDTO postEditDTO) {

        if(postEditDTO.getTagIdList().size()<1){
            LOGGER.error("tagIdList.size <1 for postId:{}",postEditDTO.getId());
            throw new RuntimeException("tagIdList.size should >=1");
        }
        LOGGER.info("edit postId:{}",postEditDTO.getId());
        postMapper.deletePostTagByPostId(postEditDTO.getId());
        postMapper.updatePost(postEditDTO);
        postMapper.insertPostTag(postEditDTO.getId(), postEditDTO.getTagIdList());

    }

    @Override
    public void createPost(PostCreateDTO postCreateDTO) {
        if(postCreateDTO.getTagIdList().size()<1){
            LOGGER.error("tagIdList.size <1 ");
            throw new RuntimeException("tagIdList.size should >=1");
        }
        LOGGER.info("create post for title:{}",postCreateDTO.getTitle());
        postMapper.insertPost(postCreateDTO);
        Integer postId = postMapper.selectPostIdByTitle(postCreateDTO.getTitle());
        LOGGER.info("createPost postId：{} getTagIdList：{}",postId,postCreateDTO.getTagIdList());
        postMapper.insertPostTag(postId,postCreateDTO.getTagIdList());

    }

    @Override
    public List<PostCard> searchPost(String term) {
        return postMapper.search(term);
    }

    @Override
    public void removePost(Integer postId) {
        LOGGER.warn("remove postId:{}",postId);
        postMapper.deletePostById(postId);
        postMapper.deletePostTagByPostId(postId);
    }

    @Override
    public Integer insertTag(String tagName) {
        LOGGER.info("insert tag:{}",tagName);
        return postMapper.insertTag(tagName);
    }

    @Override
    public Integer deleteTagById(Integer tagId) {
        LOGGER.warn("delete tagId:{}",tagId);

        Integer count = postMapper.countTagPost(tagId);
        if(count>0){
            LOGGER.error("this tag has posts,pls delete posts first");
            throw new RuntimeException("this tag has posts,pls delete posts first");
        }

        return postMapper.deleteTag(tagId);
    }

    @Override
    public Integer editTag(Integer tagId, String tagName) {
        LOGGER.info("change tagId:{} to new tagName:{}",tagId,tagName);
        return postMapper.editTag(tagId,tagName);
    }


}
