package tw.com.gbshop.controller.f01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tw.com.core.controller.BaseAction;

@Controller
@RequestMapping(value="/F010001_SCN1")
public class F010001_SCN1_Action extends BaseAction{
	private final static Logger logger = LoggerFactory.getLogger(F010001_SCN1_Action.class);
	
	@RequestMapping()
	public ModelAndView index(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		logger.debug("");
 
		ModelAndView mv = new ModelAndView("f01/F010001_SCN1");
		mv.addObject("name", name);
		return mv;
	}
}
