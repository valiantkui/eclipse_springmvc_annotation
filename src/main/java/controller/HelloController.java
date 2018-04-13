package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


/**
 * 基于注解的方法配置Controller
 * 
 * 如何写一个处理器
 * 1.不用实现Controller接口
 * 2.可以在处理器类当中，添加多个方法
 * 3.方法名不做要求,返回类型可以是ModelAndView，也可以是String
 * 4.使用@Controller,将该处理器纳入容器进行管理，（也就是说，spring配置文件不用配置该处理器了）
 * 5.使用@RequestMapping,告诉前端控制器（DispatcherServlet），
 * 		请求路径与《处理器的方法》的对应关系。(spring配置文件不用配置HandlerMapping了)。
 *
 */
@Controller
@RequestMapping("/demo")	//该注解也可以加载类名之前，表示请求的路径为/demo/hello.do;
public class HelloController {
	
	@RequestMapping("/hello.do")
	public String hello() {
		System.out.println("hello()");
		return "hello";
	}
	
	@RequestMapping("/login.do")
	public String toLogin() {
		
		System.out.println("toLogin()");
		return "login";
	}
	
	
	/**
	 * 读取请求参数值的第一种方式：
	 * 	通过request对象获取
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String login1(HttpServletRequest request) {
		System.out.println("login1()");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println("账号："+id+",密码："+password);
		return "index";
	}
	
	
	
	/**
	 * 读取请求参数值的第二种方式：
	 * 使用@RequestParam注解，将该注解添加到方法形参里
	 * 这种方式使用程度不是很多
	 * @return
	 */
	@RequestMapping("/toLogin2.do")
	public String login2(
			@RequestParam("id") String adminCode, String password) {
		
		System.out.println("login2()");
		System.out.println("账号："+adminCode+",密码："+password);
		return "index";
	}
	
	
	
	/**
	 * 读取请求参数值的第三种方式：
	 * 使用javabean封装请求参数,对此bean的要求：
	 * 1.属性名和请求参数名一样
	 * 2.具有get,set方法
	 * 
	 * @return
	 */
	@RequestMapping("/toLogin3.do")
	public String login3(AdminParam ap) {
		
		System.out.println("login3()");
		
		System.out.println("账号："+ap.getId()+",密码："+ap.getPassword());
		return "index";
	}
	
	
	
	/**
	 * 向页面传值的第一种方式：
	 * 使用request
	 * @return
	 */
	@RequestMapping("/toLogin4.do")
	public String login4(AdminParam ap,HttpServletRequest request) {
		System.out.println("login4()");
		System.out.println("接收到的账号和密码："+ap);
		
		//向页面传值：
		//将数据绑定到request中
		request.setAttribute("message", "你好，浏览器，我是服务器，哈哈哈！！！");
		//springmvc默认使用转发
		return "index";
		
	}
	
	/**
	 * 向页面传值的第二种方式：
	 * 使用ModelAndView
	 * @param ap获取请求参数值
	 * @return
	 */
	@RequestMapping("/toLogin5.do")
	public ModelAndView login5(AdminParam ap) {
		System.out.println("login5()");
		System.out.println("接收到的账号和密码："+ap);
		
		
		Map<String,Object> data = new HashMap<String,Object>();
		
		//相当于request.setAttribute("message","你好，浏览器，我是服务器.......")
		data.put("message", "你好，浏览器，我是服务器，正在使用向页面传值的 第二种方法：ModelAndView");
		//构造ModelAndView对象
		ModelAndView mav = new ModelAndView("index",data);//index为视图名
		return mav;
	}
	
	
	
	/**
	 * 向页面传值的第三种方式：
	 * 使用ModelMap:
	 * @param ap获取请求参数值
	 * @param mm获取请求参数值
	 * @return返回视图名
	 */
	@RequestMapping("/toLogin6.do")
	public String login6(AdminParam ap, ModelMap mm) {
		System.out.println("login6()");
		System.out.println("接收到的账号和密码："+ap);
		
		//相当于request.setAttribute()....
		mm.addAttribute("message","你好，浏览器，我是服务器，正在使用向页面传值的 第三种方法：ModelMap");
		//默认转发
		return "index";
	}
	
	/**
	 * 向页面传值的第四种方式：
	 * 使用session(session生命周期过长，一般不建议使用，一般建议使用生命周期短的，例如requestd)
	 * @param ap获取请求参数值
	 * @param session 
	 * @return
	 */
	@RequestMapping("/toLogin7.do")
	public String login7(AdminParam ap, HttpSession session) {
		System.out.println("login7()");
		System.out.println("接收到的账号和密码："+ap);
		
		session.setAttribute("message","你好，浏览器，我是服务器，正在使用向页面传值的 第四种方法：session");
		//默认转发
		return "index";
	}
	
	/**
	 * 重定向第一种：
	 * 返回类型为字符串：
	 * @return
	 */
	@RequestMapping("/toLogin8.do")
	public String login8() {
		System.out.println("login8()");
		//重定向，返回字符串前面加上redirect：
		//建议地址之前加上/，代表着项目的路径（项目路径可省略）
		return "redirect:/test.html";
	}
	
	/**
	 * 重定向第二种：
	 * 返回类型为ModelAndView
	 * @return
	 */
	@RequestMapping("/toLogin9.do")
	public ModelAndView login9() {
		System.out.println("login9()");
		
		//这种方法中的重定向地址建议绝对路径地址形式
		RedirectView rv = new RedirectView("/springmvc_annotation/test.html");
		return new ModelAndView(rv);
	}
}
