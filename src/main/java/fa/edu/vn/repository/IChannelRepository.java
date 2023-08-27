package fa.edu.vn.repository;

import fa.edu.vn.entites.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChannelRepository extends JpaRepository<Channel,Integer> {



	Channel getByChannelId(Integer channelId);
}
