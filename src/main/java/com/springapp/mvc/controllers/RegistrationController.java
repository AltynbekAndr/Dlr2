package com.springapp.mvc.controllers;

import com.springapp.mvc.model_for_users.GroupsUs;
import com.springapp.mvc.service.MyServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RegistrationController {
	@Autowired
	MyServiceClass myServiceClass;
	@ResponseBody
	@RequestMapping(value = "saveGroupsUs",method = RequestMethod.POST)
	public Object saveGroupsUs(@RequestBody GroupsUs groupsUs) {
		if(groupsUs!=null){
			myServiceClass.save(groupsUs);
			return 200;
		}
		return 500;
	}








}