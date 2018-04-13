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
 * ����ע��ķ�������Controller
 * 
 * ���дһ��������
 * 1.����ʵ��Controller�ӿ�
 * 2.�����ڴ������൱�У���Ӷ������
 * 3.����������Ҫ��,�������Ϳ�����ModelAndView��Ҳ������String
 * 4.ʹ��@Controller,���ô����������������й�����Ҳ����˵��spring�����ļ��������øô������ˣ�
 * 5.ʹ��@RequestMapping,����ǰ�˿�������DispatcherServlet����
 * 		����·���롶�������ķ������Ķ�Ӧ��ϵ��(spring�����ļ���������HandlerMapping��)��
 *
 */
@Controller
@RequestMapping("/demo")	//��ע��Ҳ���Լ�������֮ǰ����ʾ�����·��Ϊ/demo/hello.do;
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
	 * ��ȡ�������ֵ�ĵ�һ�ַ�ʽ��
	 * 	ͨ��request�����ȡ
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String login1(HttpServletRequest request) {
		System.out.println("login1()");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println("�˺ţ�"+id+",���룺"+password);
		return "index";
	}
	
	
	
	/**
	 * ��ȡ�������ֵ�ĵڶ��ַ�ʽ��
	 * ʹ��@RequestParamע�⣬����ע����ӵ������β���
	 * ���ַ�ʽʹ�ó̶Ȳ��Ǻܶ�
	 * @return
	 */
	@RequestMapping("/toLogin2.do")
	public String login2(
			@RequestParam("id") String adminCode, String password) {
		
		System.out.println("login2()");
		System.out.println("�˺ţ�"+adminCode+",���룺"+password);
		return "index";
	}
	
	
	
	/**
	 * ��ȡ�������ֵ�ĵ����ַ�ʽ��
	 * ʹ��javabean��װ�������,�Դ�bean��Ҫ��
	 * 1.�����������������һ��
	 * 2.����get,set����
	 * 
	 * @return
	 */
	@RequestMapping("/toLogin3.do")
	public String login3(AdminParam ap) {
		
		System.out.println("login3()");
		
		System.out.println("�˺ţ�"+ap.getId()+",���룺"+ap.getPassword());
		return "index";
	}
	
	
	
	/**
	 * ��ҳ�洫ֵ�ĵ�һ�ַ�ʽ��
	 * ʹ��request
	 * @return
	 */
	@RequestMapping("/toLogin4.do")
	public String login4(AdminParam ap,HttpServletRequest request) {
		System.out.println("login4()");
		System.out.println("���յ����˺ź����룺"+ap);
		
		//��ҳ�洫ֵ��
		//�����ݰ󶨵�request��
		request.setAttribute("message", "��ã�����������Ƿ�������������������");
		//springmvcĬ��ʹ��ת��
		return "index";
		
	}
	
	/**
	 * ��ҳ�洫ֵ�ĵڶ��ַ�ʽ��
	 * ʹ��ModelAndView
	 * @param ap��ȡ�������ֵ
	 * @return
	 */
	@RequestMapping("/toLogin5.do")
	public ModelAndView login5(AdminParam ap) {
		System.out.println("login5()");
		System.out.println("���յ����˺ź����룺"+ap);
		
		
		Map<String,Object> data = new HashMap<String,Object>();
		
		//�൱��request.setAttribute("message","��ã�����������Ƿ�����.......")
		data.put("message", "��ã�����������Ƿ�����������ʹ����ҳ�洫ֵ�� �ڶ��ַ�����ModelAndView");
		//����ModelAndView����
		ModelAndView mav = new ModelAndView("index",data);//indexΪ��ͼ��
		return mav;
	}
	
	
	
	/**
	 * ��ҳ�洫ֵ�ĵ����ַ�ʽ��
	 * ʹ��ModelMap:
	 * @param ap��ȡ�������ֵ
	 * @param mm��ȡ�������ֵ
	 * @return������ͼ��
	 */
	@RequestMapping("/toLogin6.do")
	public String login6(AdminParam ap, ModelMap mm) {
		System.out.println("login6()");
		System.out.println("���յ����˺ź����룺"+ap);
		
		//�൱��request.setAttribute()....
		mm.addAttribute("message","��ã�����������Ƿ�����������ʹ����ҳ�洫ֵ�� �����ַ�����ModelMap");
		//Ĭ��ת��
		return "index";
	}
	
	/**
	 * ��ҳ�洫ֵ�ĵ����ַ�ʽ��
	 * ʹ��session(session�������ڹ�����һ�㲻����ʹ�ã�һ�㽨��ʹ���������ڶ̵ģ�����requestd)
	 * @param ap��ȡ�������ֵ
	 * @param session 
	 * @return
	 */
	@RequestMapping("/toLogin7.do")
	public String login7(AdminParam ap, HttpSession session) {
		System.out.println("login7()");
		System.out.println("���յ����˺ź����룺"+ap);
		
		session.setAttribute("message","��ã�����������Ƿ�����������ʹ����ҳ�洫ֵ�� �����ַ�����session");
		//Ĭ��ת��
		return "index";
	}
	
	/**
	 * �ض����һ�֣�
	 * ��������Ϊ�ַ�����
	 * @return
	 */
	@RequestMapping("/toLogin8.do")
	public String login8() {
		System.out.println("login8()");
		//�ض��򣬷����ַ���ǰ�����redirect��
		//�����ַ֮ǰ����/����������Ŀ��·������Ŀ·����ʡ�ԣ�
		return "redirect:/test.html";
	}
	
	/**
	 * �ض���ڶ��֣�
	 * ��������ΪModelAndView
	 * @return
	 */
	@RequestMapping("/toLogin9.do")
	public ModelAndView login9() {
		System.out.println("login9()");
		
		//���ַ����е��ض����ַ�������·����ַ��ʽ
		RedirectView rv = new RedirectView("/springmvc_annotation/test.html");
		return new ModelAndView(rv);
	}
}
