package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import form.LoginForm;
import repository.UserRepository;
import model.User;
import validation.GroupOrder;
import com.Bootstrap_container;

import component.AsyncTest;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	UserRepository userRep;
	
	@Autowired
	AsyncTest asyncTest; 
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	 public String index(Model model) {
		 return "index";
	 }
	 
	 @RequestMapping(value = "/asynctest", method = RequestMethod.GET)
	 public String asynctest(Model model) throws IOException, InterruptedException {
		 asyncTest.run();
		 return "top";
	 }
	 
	 @RequestMapping(value = "/logout", method = RequestMethod.GET)
	 public String logout(Model model) {
		 return "redirect:/";
	 }
	 

	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	 public String login(Model model, @Validated(GroupOrder.class) @ModelAttribute("loginForm") 
			 			LoginForm loginForm
		 				, BindingResult result	 
		 				, HttpSession session
//		 				, WebRequest webRequest
		 				) {
		 if(result.hasErrors()) {
			 return "index";
		 }
	  

		 List<User> userList = userRep.findByIdAndPassword(loginForm.getUserId(), loginForm.getLoginPassword());
		 
		 if(userList.size() > 0) {
			 String userName =userList.get(0).getName();
			 model.addAttribute("userName", userName);
			 
			 //idと名前を保持

		 	session.setAttribute("session1", userList.get(0).getId());
		 	session.setAttribute("session2", userName);
//			webRequest.setAttribute("session2", userName,WebRequest.SCOPE_SESSION);
			Bootstrap_container container = new Bootstrap_container("Welcome",userName); 
			model.addAttribute("containerstart", container.getCotainerStart());
			model.addAttribute("titleheader", container.getHeader());
			model.addAttribute("menu", container.getMenu());
			model.addAttribute("contentsstart", container.getContetntsStart());
			model.addAttribute("contentsend", container.getContentsEnd());
			model.addAttribute("containerend", container.getCotainerEnd());
			 
			
			return "top";
		 } else {
			 	model.addAttribute("msg", "ユーザーIDかパスワードが間違っています。");
		 		return "index";
		 }

	  
	 }
	    @Autowired
	    private Properties applicationProperties;

	 
	    @RequestMapping(value = "property", method = RequestMethod.GET)
	    public String home(Locale locale, Model model) {
	 
	        String message = 
	                applicationProperties.getProperty("app.test");
//	        logger.info("message : " + message);
	 
	        model.addAttribute("message", message );
	        return "index";
	    }
	    
	    @RequestMapping(value="upload/finish")
	    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
	      File saveFile = new File("c:\\tmp\\upload.txt");
	      FileUtils.copyInputStreamToFile(file.getInputStream(), saveFile);
	      return "index";
	    }
	 
}