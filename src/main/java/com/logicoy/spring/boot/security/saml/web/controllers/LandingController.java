/*
 * Copyright 2019 Ansuman Nayak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.logicoy.spring.boot.security.saml.web.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logicoy.spring.boot.security.saml.web.stereotypes.CurrentUser;

@Controller
public class LandingController {
	
	// Logger
	private static final Logger LOG = LoggerFactory
			.getLogger(LandingController.class);

	@RequestMapping("/landing")
	public String landing(@CurrentUser User user, Model model,HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Enumeration enumeration = request.getHeaderNames();
	  
	    while(enumeration.hasMoreElements()){
	        String parameterName = (String) enumeration.nextElement();
	        System.out.println(parameterName+request.getHeader(parameterName));
	    }
		if (auth == null)
			LOG.debug("Current authentication instance from security context is null");
		else
			LOG.debug("Current authentication instance from security context: "
					+ this.getClass().getSimpleName());
		model.addAttribute("username", 	user.getUsername());
		return "pages/landing";
	}

}
