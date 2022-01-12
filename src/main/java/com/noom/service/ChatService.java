package com.noom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatService {
	
	public final List<String> lst = new ArrayList<>();
	
	public void addUserList(String id) {
		lst.add(id);
	}
	
	public List<String> getUserList(String id) {
		List<String> temp = new ArrayList<>();
		temp.addAll(lst);
		temp.remove(id);
		return temp;
	}
}
