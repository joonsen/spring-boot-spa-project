package kr.jobtc.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.jobtc.guestbook.GPageVo;
import kr.jobtc.guestbook.GuestbookVo;

@Repository
@Mapper
public interface GuestbookMapper {
	public int totSize(GPageVo gVo);
	public List<GuestbookVo> list(GPageVo vo);
	public int insert(GuestbookVo vo);
	public int delete(GuestbookVo vo);
	public int update(GuestbookVo vo);
	public List<GuestbookVo> select10();
}
