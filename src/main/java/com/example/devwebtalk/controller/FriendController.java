package com.example.devwebtalk.controller;

import com.example.devwebtalk.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class FriendController {

	final FriendService friendService;

	public FriendController(FriendService friendService) {
		this.friendService = friendService;
	}

	@GetMapping(value = "/user/friendList")
	public String friendListView() {
		return "/user/friendList";
	}

}
