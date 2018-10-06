package test.jenkins.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class HelloWorldController {

	@RequestMapping(path="/hello/{name}")
	public String helloWorld(@PathVariable String name) {
		return String.format("Hello, %s", name);
	}
}
