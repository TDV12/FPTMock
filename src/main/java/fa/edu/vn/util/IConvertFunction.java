package fa.edu.vn.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IConvertFunction<T> {

    Page<T> convertListToPage(Pageable pageable, List<T> list);
}
