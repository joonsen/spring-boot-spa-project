package kr.jobtc.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.jobtc.mybatis.GuestbookMapper;

@Component
@Transactional
//component는 memory에만 존재. repository엔 하드디스크(메모리엔 안올림)
public class GuestbookDao {
	GPageVo gVo;
	
	@Autowired
	GuestbookMapper mapper;
	
	@Autowired
	PlatformTransactionManager manager;
	
	TransactionStatus status;
	
	public int getTotSize(GPageVo gVo) {
		int totSize = 0;
		totSize = mapper.totSize(gVo);
		
		return totSize;
	}
	
	public List<GuestbookVo> select(GPageVo gVo) {
		List<GuestbookVo> list = null;
		int totSize = getTotSize(gVo);
		gVo.setTotSize(totSize);
		System.out.println("gVo.totSize : " + gVo.totSize);
		this.gVo = gVo;
		list = mapper.list(gVo);
		System.out.println("list : " + list);
		
		return list;
	}
	
	public boolean insert(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
				
		int cnt = mapper.insert(vo);
		
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return b;
	}
	
	public boolean delete(GuestbookVo vo) {
		boolean b = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.delete(vo);
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return b;
	}
	
	public boolean update(GuestbookVo vo) {
		boolean b = false;
		
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.update(vo);
		
		System.out.println("DOC : "+vo.getDoc());
		if(cnt>0) {
			b=true;
			manager.commit(status);
		}else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return b;
	}
	
	public List<GuestbookVo> select10() {
		List<GuestbookVo> list = null;
		list = mapper.select10();
		System.out.println("list : " + list);
		
		return list;
	}
	
	

	public GPageVo getgVo() {return gVo;}
	
	
}










