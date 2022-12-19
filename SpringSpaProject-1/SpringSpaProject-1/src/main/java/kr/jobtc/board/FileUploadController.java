package kr.jobtc.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	static String path = "C:\\Users\\joons\\eclipse-workspace\\SpringSpaProject-1\\src\\main\\resources\\static\\upload\\";
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_insertR")
	public synchronized String insertR(@RequestParam("attFile")List<MultipartFile> mul, 
						@ModelAttribute BoardVo vo) {/*여기가 포인트*/
		//상호배타적 동기화
		String msg = "";
		try {
			System.out.println("id : " + vo.getId());
			System.out.println("subject : " + vo.getSubject());
			List<AttVo> attList = new ArrayList<AttVo>();
			
			// 본문을 저장
			attList = fileupload(mul);
			vo.setAttList(attList);
			boolean flag = service.insertR(vo);
			if(!flag) {
					msg = "저장중 오류 발생";
			}
			//잘라내었다
//			attList = fileupload(mul);
////			vo.setAttList(attList);
//			service.insertAttList(attList);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return msg;
		//ajax를 쓰기때문에 무의미
	}
	
	@RequestMapping("/board/board_updateR")
	public String updateR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo,
			@RequestParam(name="delFile", required=false) String[] delFiles) {
		//modelAttribute는 getter setter를 받을때,
		//그렇지 않을때는 param으로 받는게 편하다
		
		String msg = "";
		
		try {
			List<AttVo> attList = fileupload(mul);
			bVo.setAttList(attList);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		boolean flag = service.updateR(bVo, delFiles);
		if( !flag) msg = "수정중 오류 발생";
		
		return msg;
	}
	
	@RequestMapping("/board/board_replR")
	public synchronized String replR(@RequestParam("attFile")List<MultipartFile> mul, 
						@ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo) {/*여기가 포인트*/
		//상호배타적 동기화
		
		String msg = "";
		try {
			List<AttVo> attList = new ArrayList<AttVo>();
			//잘라내었다
			attList = fileupload(mul);
			bVo.setAttList(attList);
			
			// 본문을 저장
			boolean flag = service.replR(bVo);
			if(!flag) msg= "저장중 오류 발생";
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return msg;
		//ajax를 쓰기때문에 무의미
	}
	
	//file upload 공통부분(insertR, updateR, 
	public List<AttVo> fileupload(List<MultipartFile> mul) throws Exception{
		List<AttVo> attList = new ArrayList<AttVo>();
		
		for(MultipartFile m : mul) {
			if(m.isEmpty()) continue;
			
			UUID uuid = UUID.randomUUID();
			String oriFile = m.getOriginalFilename();
			String sysFile = "";
			File temp = new File(path + oriFile);
			m.transferTo(temp);
			sysFile = (uuid.getLeastSignificantBits()*-1) + "-" + oriFile;
			File f = new File(path + sysFile);
			temp.renameTo(f);
			
			AttVo attVo = new AttVo();
			attVo.setOriFile(oriFile);
			attVo.setSysFile(sysFile);
			
			attList.add(attVo);
			System.out.println(m.getOriginalFilename());
			System.out.println(uuid.getLeastSignificantBits());
		}
		
		return attList;
	}
	
}











