package tw.com.gbshop.controller.f00;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.com.core.controller.BaseAction;

@Controller
public class F000001_SCN1_Action extends BaseAction {
	private final static Logger logger = LoggerFactory.getLogger(F000001_SCN1_Action.class);
	
	@RequestMapping(value="/login")
	public ModelAndView login() {
		logger.debug("");
		ModelAndView mv = new ModelAndView("f00/login");
		return mv;
	}
	
	@RequestMapping(value="/loginProcess")
	public ModelAndView loginProcess() {
		logger.debug("");
		ModelAndView mv = new ModelAndView("f00/login");
		return mv;
	}
	
	
}
