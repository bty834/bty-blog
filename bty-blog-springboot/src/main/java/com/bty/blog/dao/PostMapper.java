package com.bty.blog.dao;

import com.bty.blog.entity.dto.CommentDTO;
import com.bty.blog.entity.dto.PostCreateDTO;
import com.bty.blog.entity.dto.PostEditDTO;
import com.bty.blog.entity.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bty
 * @date 2022/11/28
 * @since 1.8
 **/
public interface PostMapper {


    List<PostCard> selectPostCardList();

    PostDetail selectPostDetailById(Integer id);

    List<Integer> selectPostIdList();

    List<Integer> selectTagIdList();

    List<TagVO> selectTagListByPostId(Integer postId);

    List<PostVO> selectRecentPostList(Integer limit);

    List<PostCard> selectPostCardListByTagId(@Param("tagId") Integer tagId);

    List<TagVO> selectTagList();

    List<CommentVO> selectCommentByPostId(Integer postId);
    Integer insertComment(@Param("comment") CommentDTO comment);

    Integer insertPost(@Param("post")PostCreateDTO post);

    Integer updatePost(@Param("post")PostEditDTO post);

    Integer insertPostTag(@Param("postId")Integer postId,@Param("tagIdList")List<Integer> tagIdList);

    void deletePostById(Integer postId);

    void deletePostTagByPostId(Integer postId);

    Integer selectPostIdByTitle(String title);

    Integer insertTag(String tagName);

    Integer countTagPost(Integer tagId);

    Integer deleteTag(Integer tagId);

    Integer editTag(@Param("tagId") Integer tagId, @Param("tagName") String tagName);

    List<PostCard> search(String term);
}
