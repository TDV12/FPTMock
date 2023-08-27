package fa.edu.vn.service.impl;

import fa.edu.vn.dto.AuditDto;
import fa.edu.vn.entites.Audit;
import fa.edu.vn.mapper.AuditMapper;
import fa.edu.vn.repository.IAuditRepository;
import fa.edu.vn.service.IAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements IAuditService {
    @Autowired
    private IAuditRepository auditRepository;

    @Autowired
    private AuditMapper auditMapper;

    @Override
    public void deleteAllAudit(List<Audit> audits) {
        auditRepository.deleteAll(audits);
    }

    @Override
    public List<Audit> findAllAuditByDeleteFlagAndClassId(String status, Integer id) {
        return auditRepository.findAllAuditByDeleteFlagAndClassId(status, id);
    }

    @Override
    public List<AuditDto> mappAllAuditByDeleteFlagAndClassId(String status, Integer id) {
        List<Audit> allAuditByDeleteFlagAndClassId = findAllAuditByDeleteFlagAndClassId(status, id);
        return auditMapper.toDtos(allAuditByDeleteFlagAndClassId);
    }
}
