package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/upload")
public class UploadControler {

	@RequestMapping(method = RequestMethod.GET)
	public String uploadGet(HttpServletRequest request) {
		return "upload";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView uploadPost(@RequestParam MultipartFile file, Model model) {
		ModelAndView mv = new ModelAndView("index");

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(file.getInputStream(), "JISAutoDetect"));) {

			StringBuilder sb = new StringBuilder();

			// ファイル名出力
			sb.append(file.getOriginalFilename());
			sb.append("<br />");

			// ファイル内容出力
			char[] b = new char[1024];
			int line;
			while (0 <= (line = reader.read(b))) {
				sb.append(b, 0, line);
			}
			mv.addObject("msg", sb.toString().replaceAll("\r\n", "<br />"));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return mv;
	}
}