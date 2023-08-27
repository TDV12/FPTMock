package fa.edu.vn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.edu.vn.entites.Channel;
import fa.edu.vn.repository.IChannelRepository;
import fa.edu.vn.service.IChannelService;

@Service
public class ChannelServiceImpl implements IChannelService {
	
	@Autowired
	private IChannelRepository channelRepository;

	@Override
	public Channel save(List<Channel> channels) {
		
		return (Channel) channelRepository.saveAll(channels);
	}

	@Override
	public List<Channel> findAll() {
		// TODO Auto-generated method stub
		return channelRepository.findAll();
	}

}
