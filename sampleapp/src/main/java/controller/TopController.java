package controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.Script;
import com.Bootstrap_container;
import com.Head;

@Controller
public class TopController {

//	private String script="";
	@Autowired
	HttpSession session;
	private Bootstrap_container container;
	private Script script=new Script();
	private Head head;
	private String contents1="注文入力";
	private String contents2="注文調整";
	private String contents3="注文調整結果";
	
	 @RequestMapping(value = "/display1", method = RequestMethod.GET)
	 public String display1(Model model) {
		 
		 model.addAttribute("script", script.getScript());
		 head = new Head(contents1);
		 model.addAttribute("head",head.getHead());
		 container = new Bootstrap_container(contents1,session.getAttribute("session2").toString()); 
	

		 modelmake(model);
		 return "display1";
	 }
	 @RequestMapping(value = "/display2", method = RequestMethod.GET)
	 public String display2( Model model) {
		 model.addAttribute("script", script.getScript());
		 head = new Head(contents2);
		 model.addAttribute("head",head.getHead());
		 container = new Bootstrap_container(contents2,session.getAttribute("session2").toString()); 
		 modelmake(model);
		 return "display2";
	 }
	 @RequestMapping(value = "/adjustmentresult", method = RequestMethod.GET)
	 public String adjustmentResult( Model model) {
		 model.addAttribute("script", script.getScript());
		 head = new Head(contents3);
		 model.addAttribute("head",head.getHead());
		 container = new Bootstrap_container(contents3,session.getAttribute("session2").toString()); 
		 
		 LinkedHashMap<Integer, String> states = new LinkedHashMap<Integer, String>();
		 states.put(1, "Alabama");
		 states.put(2, "Alaska");
		 states.put(3, "Arizona");
		 states.put(4, "Arkansas");
		 states.put(5, "California");
		 model.addAttribute("states",states);
		 
		 
		 modelmake(model);
		 return "result";
	 }
	 

	 
	 private void modelmake(Model model) {
		 model.addAttribute("containerstart", container.getCotainerStart());
		 model.addAttribute("titleheader", container.getHeader());
		 model.addAttribute("menu", container.getMenu());
		 model.addAttribute("contentsstart", container.getContetntsStart());
		 model.addAttribute("contentsend", container.getContentsEnd());
		 model.addAttribute("containerend", container.getCotainerEnd());
	 }
	 
	 private Map<Integer,String> getSelectedItems(){
		 Map<Integer, String> selectMap = new LinkedHashMap<Integer, String>();
	     selectMap.put(1, "選択肢Ａは、これですよ");
	     selectMap.put(2, "選択肢Ｂは、これですよ");
	     selectMap.put(3, "選択肢Ｃは、これですよ");
	     selectMap.put(4, "選択肢Ｄは、これですよ");
	     selectMap.put(5, "選択肢Ｅは、これですよ");
	     return selectMap;
	 }
	 
}
