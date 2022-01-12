package com.noom.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.noom.model.ChatDto;
import com.noom.model.ChatMessage;
import com.noom.service.ChatService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/msg")
public class ChatController {
	private final ChatService chatService;
	private final SimpMessagingTemplate simp;
	@GetMapping("")
    public ModelAndView chatMain(@RequestParam String id){
        ModelAndView mav = new ModelAndView();
        System.out.println(id);
        chatService.addUserList(id);
        mav.setViewName("ChatMain");
        mav.addObject("user_id",id);
        mav.addObject("ChatDto",new ChatDto());
        
        return mav;
    }
	
	@GetMapping("/list")
	public List<String> getUserList(@RequestParam String id){
		return chatService.getUserList(id);
	}
	
    @PostMapping("/chat")
    public ModelAndView chattingRoom(ChatDto chatdto ){
        ModelAndView mav = new ModelAndView();
        mav.addObject("sender",chatdto.getSender());
        mav.addObject("receiver",chatdto.getReceiver());
        mav.setViewName("chatRoom");
   
        return mav;
    }
    
    @MessageMapping("/chat.send")
    public void messgeSend(ChatMessage chatMessage) {
    	System.out.println(chatMessage.getMessage());
    	System.out.println(chatMessage.getReceiver());
    	System.out.println(chatMessage.getSender());
    	System.out.println(chatMessage.getType());
    	simp.convertAndSend("/topic/"+chatMessage.getSender(),chatMessage);
    	simp.convertAndSend("/topic/"+chatMessage.getReceiver(),chatMessage);
    }
  
}



