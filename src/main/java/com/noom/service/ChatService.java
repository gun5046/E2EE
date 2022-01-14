package com.noom.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatService {
	
	public final Set<String> lst = new HashSet<>();
	
	public void addUserList(String id) {
		lst.add(id);
	}
	
	public Set<String> getUserList(String id) {
		Set<String> temp = new HashSet<>();
		temp.addAll(lst);
		temp.remove(id);
		return temp;
	}
}
