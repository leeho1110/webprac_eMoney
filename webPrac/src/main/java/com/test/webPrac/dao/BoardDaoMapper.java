package com.test.webPrac.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.webPrac.vo.BoardVO;
import com.test.webPrac.vo.PagingVO;

@Repository
public interface BoardDaoMapper {

	int insertPost(BoardVO boardVO);

	int selectTotalBoardCnt();
	
	List<BoardVO> selectTotalBoardList(PagingVO pagingVO);

	BoardVO selectViewOfPost(int post_num);

	int deletePost(int post_num);

	int updatePost(BoardVO boardVO);
}
