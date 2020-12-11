package com.test.webPrac.dao;

import org.springframework.stereotype.Repository;

import com.test.webPrac.vo.BoardVO;

@Repository
public interface BoardDaoMapper {

	int insertPost(BoardVO boardVO);

	int getTotalBoardCnt();
}
