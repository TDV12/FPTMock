package fa.edu.vn.service.impl;

import fa.edu.vn.dto.BudgetDto;
import fa.edu.vn.entites.Budget;
import fa.edu.vn.entites.BudgetDetail;
import fa.edu.vn.mapper.BudgetMapper;
import fa.edu.vn.repository.IBudgetRepository;
import fa.edu.vn.repository.IBugdetDetailRepository;
import fa.edu.vn.service.IBudgetDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetDetailServiceImpl implements IBudgetDetailService {

    @Autowired
    private IBugdetDetailRepository bugdetDetailRepository;

    @Autowired
    private BudgetMapper budgetMapper;

    @Override
    public void deleteAllBudgetDetail(List<BudgetDetail> budgetDetailList) {
        bugdetDetailRepository.deleteAll(budgetDetailList);
    }


    @Override
    public List<BudgetDetail> findAllBudgetDetailById(List<Integer> ids) {
        return bugdetDetailRepository.findAllById(ids);
    }

    @Override
    public List<BudgetDetail> findAllBudgetDetailByStatusAndClassId(String status, Integer id) {
        return bugdetDetailRepository.findAllBudgetDetailByStatusAndClassId(status, id);
    }

    @Override
    public List<BudgetDto> mapAllBudgetDtoByStatusAndClassId(String status, Integer id) {
        List<BudgetDetail> allBudgetDetailByStatusAndClassId = bugdetDetailRepository.findAllBudgetDetailByStatusAndClassId(status, id);
        return budgetMapper.toDtos(allBudgetDetailByStatusAndClassId);
    }
}
