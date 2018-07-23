package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Excel {
	@RequestMapping(value="excel",  method = RequestMethod.GET)
	public String uploadGet(Model model) {
		try {
			Workbook workbook = WorkbookFactory.create(new File("C:\\tmp\\upload.xlsx"));
			
			// 取得したいシートが何番目かわかっている場合
			// シート番号はゼロベース
			Sheet sheet = workbook.getSheetAt(0);
			
			Cell cell = sheet.getRow(0).getCell(0);
			model.addAttribute("excel", cell.getStringCellValue());
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// java.io.inputStream
//		InputStream inputStream = new FileInputStream("ファイルパス");
//		Workbook workbook = WorkbookFactory.create(inputStream);
		
		return "index";
	}

}
