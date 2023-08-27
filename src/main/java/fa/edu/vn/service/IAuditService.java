package fa.edu.vn.service;

import fa.edu.vn.dto.AuditDto;
import fa.edu.vn.entites.Audit;

import java.util.List;

public interface IAuditService {

    void deleteAllAudit(List<Audit> audits);

    List<Audit> findAllAuditByDeleteFlagAndClassId(String status,Integer id);

    List<AuditDto> mappAllAuditByDeleteFlagAndClassId(String status,Integer id);
}
