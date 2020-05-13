package com.wenmxr.seckill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenmxr.pojo.Seckill;
import com.wenmxr.seckill.mapper.SecMapper;

@Service
public class SecService {
	@Autowired
	private SecMapper secMapper;
	public List<Seckill> queryAll() {
		
		return secMapper.selectAll();
	}
	public Seckill queryOne(Integer seckillId) {
		
		return secMapper.selectOneById(seckillId);
	}

}
