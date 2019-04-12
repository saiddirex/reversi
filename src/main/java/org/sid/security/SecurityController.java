package org.sid.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	  
	  @RequestMapping(value="/")
	  public String home() {
		  return "redirect:/login";
	  }
	  
	
}
