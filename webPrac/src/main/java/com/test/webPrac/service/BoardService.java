package com.test.webPrac.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.webPrac.vo.BoardVO;
import com.test.webPrac.vo.PagingVO;

public interface BoardService {

	int insertPost(BoardVO boardVO, HttpSession session);

	String fileUpload(HttpServletRequest request);

	ArrayList<BoardVO> getBoardList(PagingVO pagingVO);
	
}
