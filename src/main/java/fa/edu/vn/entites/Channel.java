package fa.edu.vn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.persistence.*;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Channel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Integer channelId;


    @Column
    private String remarks;

    @Column(name = "channel_name")
    private Integer channelName;
    
    @OneToMany(mappedBy = "Channel",cascade = CascadeType.ALL)
    private Set<Candidate> candidates;

	public Channel(Integer channelName) {
		super();
		this.channelName = channelName;
	}
    
    

	}
