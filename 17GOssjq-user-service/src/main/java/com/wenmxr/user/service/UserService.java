package com.wenmxr.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wenmxr.pojo.Address;
import com.wenmxr.pojo.User;
import com.wenmxr.user.mapper.UserMapper;
import com.wenmxr.utils.MapperUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public Boolean checkUserName(String userName) {
		int result = userMapper.selectUserCountByUserName(userName);
		return result == 1;// 0 or 1
	}

	public void saveUser(User user) {
		// 封装userId
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		// 加密password
		String md5Password = DigestUtils.md5Hex(user.getUserPassword());
		System.out.println("密码密文：" + md5Password);
		user.setUserPassword(md5Password);
		Timestamp userAddtime = new Timestamp(System.currentTimeMillis());
		user.setUserTime(userAddtime);
		userMapper.insertUser(user);
	}

	// @Autowired
	// private ShardedJedisPool pool;
	
	/**
	 * 1.登录校验，到数据库利用username && password加密查询数据
	 * 2.判断返回结果是否为空
	 * 		2.1为空；登录失败返回一个""的ticket
	 * 		2.2不为空；生成ticket 序列化查询的user对象
	 * 			创建jedis对象将ticket userJson set超时时间放到redis中 2小时
	 * 3.将ticket返回
	 */
	/*// 登录校验无顶替
	public String doLogin(User user) {
		// 对user对象做md5加密处理
		user.setUserPassword(DigestUtils.md5Hex(user.getUserPassword()));
		User exist = userMapper.selectUserByNameAndPassword(user);
		String ticket = "";// 准备一个ticket默认值为""/null
		if (exist == null) {
			return ticket;// 登录失败
		} else {
			// 构造ticket的值
			ticket = "EM_TICKET" + System.currentTimeMillis() + exist.getUserId();
			//Jedis jedis = new Jedis("10.42.177.125", 6379);
			//ShardedJedis jedis = pool.getResource();
			// exist user 的序列化过程
			try {
				String userJson = MapperUtil.MP.writeValueAsString(exist);
				jedis.setex(ticket, 60 * 60 * 2, userJson);
				// 将数据存储到redis中 关闭连接
				return ticket;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "";
			} finally {
				//jedis.close();
				if (jedis != null) {
					pool.returnResource(jedis);
				}
			}
		}
	}*/

	// 登录校验+顶替
	public String doLogin(User user) {
		Jedis jedis = new Jedis("47.99.106.114",6379);
		jedis.auth("sun+moon");
		user.setUserPassword(DigestUtils.md5Hex(user.getUserPassword()));
		User exist = userMapper.selectUserByNameAndPassword(user);
		if (exist == null) {// 登录失败
			return "";
		} else {// 引入顶替的逻辑
			try {// 准备2个key-value值  userLoginIdent ticket 和 ticket userJson
				String userLoginIdent = "user_login_" + exist.getUserId();
				String ticket = "EM_TICKET" + System.currentTimeMillis() + exist.getUserId();
				String userJson = MapperUtil.MP.writeValueAsString(exist);
				if (jedis.exists(userLoginIdent)) {
					// 已登录 删除上次登录的ticket
					jedis.del(jedis.get(userLoginIdent));
				}
				// 设置登录顶替相关的2个key-value
				jedis.setex(userLoginIdent, 60 * 60 * 3, ticket);
				jedis.setex(ticket, 60 * 60 * 2, userJson);
				return ticket;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "";
			} finally {
				jedis.close();
			}
		}
	}

	/*public String checkLoginUserData(String ticket) {
		//Jedis jedis = new Jedis("10.42.177.125", 6379);
		//ShardedJedis jedis = pool.getResource();
		try {
			return jedis.get(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			//jedis.close();
			if (jedis != null) {
				pool.returnResource(jedis);
			}
		}
	}*/
	public String checkLoginUserData(String ticket) {
		Jedis jedis = new Jedis("47.99.106.114",6379);
		jedis.auth("sun+moon");
		// 通过截取ticket获取userLoginIdent
		String userLoginIdent = "user_login_" + ticket.substring(22);
		// 获取剩余时间
		Long leftTime = jedis.pttl(ticket);
		// 判断是否小于30分钟
		if (leftTime < 1000 * 60 * 30) {
			// 进行续约 将超时时间加长1小时
			Long expireTime = leftTime + 1000 * 60 * 60;
			jedis.pexpire(ticket, expireTime);
			// 对userLoginIdent做续约保证
			jedis.pexpire(userLoginIdent, jedis.pttl(userLoginIdent) + 1000 * 60 * 60);
		}
		try {
			return jedis.get(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			jedis.close();
		}
	}

	public Address queryOneAddr(String userId) {
		return userMapper.queryOneAddrByUserId(userId);
	}

	public void addInfo(Address addr, String userId) {
		addr.setUserId(userId);
		userMapper.insertAddress(addr);
	}

	public void delAddrInfo(Integer addrId) {
		userMapper.deleteAddressByAddrId(addrId);
	}
}
