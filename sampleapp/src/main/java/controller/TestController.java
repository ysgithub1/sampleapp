package controller;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class TestController {
	 @RequestMapping(value = "loading", method = RequestMethod.POST)
	 public String index(Model model) {
		 sleep(5);
		 return "top";
	 }
	    private void sleep(long timeout) {
	        try {
	            TimeUnit.SECONDS.sleep(timeout);
	        } catch (InterruptedException e) {
	            Thread.interrupted();
	        }
	    }
}
