package com.wenmxr.user.controller;

import com.wenmxr.pojo.Address;
import com.wenmxr.pojo.User;
import com.wenmxr.user.service.UserService;
import com.wenmxr.utils.CookieUtils;
import com.wenmxr.utils.RandomValidateCodeUtil;
import com.wenmxr.vo.SysResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/user/manage")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 用户名的存在校验
	@RequestMapping("/checkUserName")
	public SysResult checkUserName(String userName) {
		Boolean exist = userService.checkUserName(userName);
		return exist?SysResult.build(201, "用户名已存在", null):SysResult.ok();
	}
	
	// 注册表单提交
	@RequestMapping("/save")
	public SysResult saveUser(User user) {
		try {
			System.out.println(user);
			userService.saveUser(user);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "注册失败", null);
		}
	}
	
	/**
	 * 登录校验 
	 * 在业务层中实现登录逻辑，存储redis过程
	 * 返回给控制层一个生成的redis存储ticket
	 * 登录成功，ticket是非空，登录失败返回空null/""
	 * @param user
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/login")
	public SysResult doLogin(User user, HttpServletRequest req, HttpServletResponse res) {
		System.out.println(user);
		String ticket = userService.doLogin(user);
		if (StringUtils.isNotEmpty(ticket)) { // !null && !""
			// ticket非空说明登录成功
			// cookie携带这个ticket返回浏览Cookie的工具类
			CookieUtils.setCookie(req, res, "EM_TICKET", ticket);
			return SysResult.ok();
		} else {
			return SysResult.build(201, "登录失败", null);
		}
	}
	
	// 通过js发送的参数ticket获取userJson封装返回给ajax使用
	@RequestMapping("/query/{ticket}")
	public SysResult checkLoginUserData(@PathVariable String ticket) {
		String userJson = userService.checkLoginUserData(ticket);
		if (StringUtils.isNotEmpty(userJson)) {
			return SysResult.build(200, "登录正常", userJson);
		} else {
			return SysResult.build(201, "登录失效", null);
		}
	}
	
	// 退出
	@RequestMapping("/logout")
	public SysResult logout(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.setCookie(request, response, "EM_TICKET", "", -1);
		return SysResult.ok();
	}
	
	// 查询收件人信息
	@RequestMapping("/queryOneAddr")
	public Address queryOneAddr(String userId) {
		Address address = userService.queryOneAddr(userId);
		System.out.println(address);
		return address;
	}
	
	// 添加收件人信息
	@RequestMapping("/addInfo")
	public SysResult addAddrInfo(Address addr, String userId) {
		try {
			userService.addInfo(addr, userId);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "新增地址失败", null);
		}
	}
	
	//删除收件人信息
	@RequestMapping("/delInfo")
	public SysResult delAddrInfo(Integer addrId) {
		try {
			userService.delAddrInfo(addrId);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "删除地址失败", null);
		}
	}
	@RequestMapping(value = "/getVerify")
	public String getVerify(HttpServletRequest request,HttpServletResponse response) {
		String code = null;
		try {
			Jedis jd = new Jedis("47.99.106.114",6379);
			jd.auth("sun+moon");
			response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			randomValidateCode.getRandcode(request, response);//输出验证码图片方法
			code = randomValidateCode.getCode();
			jd.setex("EM_CODE", 60*60*2, code);//设置验证码入缓存
			System.out.println(jd.get("EM_CODE"));
			System.out.println(code);
			/*session.removeAttribute("code1");
			session.setAttribute("code1", code);
			request.getSession().setAttribute("code", code);*/
		} catch (Exception e) {

		}
		return code;
	}
	//获取验证码
	@RequestMapping(value = "/code")
	public String getCode() throws InterruptedException {
		Jedis jd = new Jedis("47.99.106.114",6379);
		jd.auth("sun+moon");
		Thread.sleep(1000);
		String code = jd.get("EM_CODE");
		System.out.println(code);
		return code;
	}
}
