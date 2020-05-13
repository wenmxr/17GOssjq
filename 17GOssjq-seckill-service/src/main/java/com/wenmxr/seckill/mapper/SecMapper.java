package com.wenmxr.seckill.mapper;

import com.wenmxr.pojo.Seckill;
import com.wenmxr.pojo.Success;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SecMapper {

	List<Seckill> selectAll();

	Seckill selectOneById(Integer seckillId);

	int updateNumberBySeckillCondition(@Param("seckillId") Long seckillId, @Param("nowTime") Date nowTime);

	void insertSuccess(Success suc);

}
