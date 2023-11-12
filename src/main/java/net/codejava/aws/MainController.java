package net.codejava.aws;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class MainController {
	@GetMapping("")
	public String showHomePage() {
		return "upload";
	}
	@PostMapping("/upload")
	public String uploadFile(String description,@RequestParam("file") MultipartFile multipart,Model model) {
		String fileName = multipart.getOriginalFilename();
		System.out.println("Description"+ description);
		System.out.println("FileName"+fileName);
		String message="";
		try {
		S3Util.uploadFile(fileName, multipart.getInputStream());
		message="your file has been successfully uploaded";
		}
		catch(Exception ex) {
		message=ex.getMessage();
		}
		model.addAttribute("message",message);
		return "message";
	}
}
