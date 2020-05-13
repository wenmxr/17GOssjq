package com.wenmxr.user.mapper;

import com.wenmxr.pojo.Address;
import com.wenmxr.pojo.User;

public interface UserMapper {

	int selectUserCountByUserName(String userName);

	void insertUser(User user);

	User selectUserByNameAndPassword(User user);

	Address queryOneAddrByUserId(String userId);

	void insertAddress(Address addr);

	void deleteAddressByAddrId(Integer addrId);

}
