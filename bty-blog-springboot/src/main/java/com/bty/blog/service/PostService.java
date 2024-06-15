package com.bty.blog.service;

import com.bty.blog.entity.dto.CommentDTO;
import com.bty.blog.entity.dto.PostCreateDTO;
import com.bty.blog.entity.dto.PostEditDTO;
import com.bty.blog.entity.vo.*;

import java.util.List;

/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
public interface PostService {
    List<PostCard> selectPostCardList();

    PostDetail selectPostDetailById(Integer id);

    List<TagVO> selectTagListByPostId(Integer postId);

    List<PostVO> selectRecentPostList(Integer limit);

    List<Integer> selectPostIdList();

    List<PostCard> selectPostCardListByTagId(Integer tagId);

    List<TagVO> selectTagList();

    List<Integer> selectTagIdList();

    List<CommentVO> selectCommentListByPostId(Integer postId);

    void insertComment(CommentDTO comment);

    Integer insertPostTagList(Integer postId,List<Integer> tagIdList);

    void editPost(PostEditDTO postEditDTO);

    void createPost(PostCreateDTO postCreateDTO);

    void removePost(Integer postId);

    List<PostCard> searchPost(String term);

    Integer insertTag(String tagName);

    Integer deleteTagById(Integer tagId);

    Integer editTag(Integer tagId,String tagName);
}
