package com.test.webPrac.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.test.webPrac.vo.BoardVO;
import com.test.webPrac.vo.PagingVO;

public interface BoardService {

	int insertPost(BoardVO boardVO, HttpSession session);

	String fileUpload(HttpServletRequest request);
	
	int selectTotalBoardCnt();

	List<BoardVO> selectBoardList(PagingVO pagingVO);

	BoardVO selectPost(int post_num);

	int deletePost(int post_num);

	int updatePost(BoardVO boardVO);
	
}
