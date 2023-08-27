package fa.edu.vn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditDto {
    private Integer auditId;
    private String eventDate;
    private String eventCategory;
    private String relatedPartyPeople;
    private String action;
    private String pic;
    private String deadLine;
    private String note;
}
