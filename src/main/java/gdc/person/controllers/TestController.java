/**
 * 
 */
package gdc.person.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suhada
 *
 */
@RestController
@RequestMapping(value= {"/person/test"})
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	

}
