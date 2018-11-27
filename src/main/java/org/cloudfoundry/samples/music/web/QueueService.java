package org.cloudfoundry.samples.music.web;

import org.cloudfoundry.samples.music.domain.AlbumList;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

	@Autowired
	AmqpTemplate amqpTemplate;

	public void publish(AlbumList albumList) {
		amqpTemplate.convertAndSend("messages", albumList);
	}
	
	public AlbumList get() {
		AlbumList message = (AlbumList)amqpTemplate.receiveAndConvert("messages");
		if (message != null) {
			return message;
		} else {
			return new AlbumList("ERROR: queue is empty", "");
		}
	}
}
