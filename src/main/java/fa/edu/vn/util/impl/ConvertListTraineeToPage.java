package fa.edu.vn.util.impl;

import fa.edu.vn.entites.Trainee;
import fa.edu.vn.util.IConvertFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConvertListTraineeToPage implements IConvertFunction<Trainee> {

    @Override
    public Page<Trainee> convertListToPage(Pageable pageable, List<Trainee> list) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        Page<Trainee> trainees = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return trainees;
    }
}
