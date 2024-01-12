package com.springBoot.Minwat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.springBoot.Minwat.MultiplayerModels.AcceptanceModel;
import com.springBoot.Minwat.MultiplayerModels.Notification;
import com.springBoot.Minwat.MultiplayerModels.PathSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
@Controller
@CrossOrigin("*")
public class WebsockterController 
{
	@Autowired
	private SimpMessagingTemplate smp;
	
	
	@MessageMapping("/sendNotifiaction")
	public Notification notificationsend(@Payload Notification notify) {
		smp.convertAndSendToUser(notify.getReceiver(), "/notification", notify);//listened at "/user/{username}/notification"
		return notify;
	}
	
	@MessageMapping("/sendAcceptance")
	public AcceptanceModel acceptancesend(@Payload AcceptanceModel notify) {
		smp.convertAndSendToUser(notify.getReceiver(), "/acceptance", notify);//listened at "/user/{username}/acceptance"
		return notify;
	}
	
	@MessageMapping("/sendMyPath") //send to "/app/sendMyPath"
	public PathSender SendpathFromOthers(@Payload PathSender path) {
		smp.convertAndSendToUser(path.getPlayer2(), "/receiveotherpath", path);//listened at "/user/{username}/receiveotherpath"
		return path;
	}
	
	
}
