package gdc.person.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gdc.utility.common.Key;
import gdc.utility.dataservice.DataTransfer;

@RestController
@RequestMapping(value= {"/"})
public class DefaultController implements ErrorController{

	@RequestMapping(value= {""})
	public Object defaultRequest() {
		return "This is a GDC service (person service)";
	}

	//@RequestMapping("/error")
    //@ResponseBody
    public DataTransfer handleError(HttpServletRequest request) {
    	DataTransfer dataTransfer = new DataTransfer();
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
//        return String.format(""
//        		+ "<html>"
//        		+ "<body>"
//        		+ "<h2>Error Page</h2>"
//        		+ "<div>Status code: <b>"+statusCode+"</b></div>"
//                + "<div>Exception Message: <b>"+exception.getLocalizedMessage()+"</b></div>"
//                + "<body>"
//                + "</html>");
        dataTransfer.setStatus(statusCode+"");
        dataTransfer.addOutput(Key.ERROR, exception.getLocalizedMessage());
        dataTransfer.addOutput(Key.ERROR_LIST, exception.getLocalizedMessage());
        return dataTransfer;
    }
	
	
	
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
}
