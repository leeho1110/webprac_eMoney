package com.test.webPrac.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.webPrac.dao.BoardDaoMapper;
import com.test.webPrac.vo.BoardVO;
import com.test.webPrac.vo.MemberVO;
import com.test.webPrac.vo.PagingVO;

@Service
public class BoardServiceImpl implements BoardService {

	static final Logger logger = LoggerFactory.getLogger("emoney Web Practice");
	
	@Autowired
	private BoardDaoMapper boardDaoMapper;

	@Override
	public String fileUpload(HttpServletRequest request) {
		
		String fileInfo = "";
		String fileName = request.getHeader("file-name");
		String fileName_suffix = fileName.substring(fileName.indexOf(".") + 1).toLowerCase();

		logger.info("SET PATH OF INPUTSTREAM");
		// C:\workspace\git\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\webPrac\img\SE\
		String defaultPath = request.getSession().getServletContext().getRealPath("/");
		String filePath = defaultPath + "img" + File.separator + "SE" + File.separator;

		File file = new File(filePath);

		// 위에서 지정한 폴더 생성
		// C:\workspace\git\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\webPrac\img\SE\
		if (!file.exists()) {
			file.mkdirs();
		}

		String autoFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
		String rFileName = filePath + autoFileName;

		InputStream is;
		try {
			is = request.getInputStream();

			// 위에서 생성한 폴더에 rFilename 파일을 생성
			OutputStream os = new FileOutputStream(rFileName);

			// os가 생성한 빈 폴더에 file을 바이트로 읽어 작성함
			int num;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while ((num = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, num);
			}
			if (is != null) {
				is.close();
			}

			os.flush();
			os.close();
			logger.info("FILE CREATING SUCCESS");

		} catch (IOException e) {
			e.printStackTrace();
		}

		fileInfo += "&bNewLine=true";
		fileInfo += "&sFileName=" + fileName;
		fileInfo += "&sFileURL=/img/SE/" + autoFileName;

		return fileInfo;

	}

	@Override
	public int insertPost(BoardVO boardVO, HttpSession session) {
		logger.info("INSERT NEW POST");
		int writer = ((MemberVO)session.getAttribute("loginStatus")).getAccnt_id();
		boardVO.setWriter(writer);
		return boardDaoMapper.insertPost(boardVO);
	}

	@Override
	public ArrayList<BoardVO> getBoardList(PagingVO pagingVO) {
		// 그 뒤부터 받아온 걸 가지고 메소드 활용해서 작성하는게 중요할듯?
		logger.info("GET BOARD LIST");
		
		int total = boardDaoMapper.getTotalBoardCnt();
		
		return null;
	}

}
