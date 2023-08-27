package fa.edu.vn.service;

import java.util.List;

import fa.edu.vn.entites.Channel;

public interface IChannelService {
	
	Channel save(List<Channel> channel);
	List<Channel> findAll();
}
