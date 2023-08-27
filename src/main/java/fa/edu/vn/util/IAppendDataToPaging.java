package fa.edu.vn.util;

import org.springframework.ui.Model;

public interface IAppendDataToPaging {

    void appendDataToPaging(Model model, int totalPage, int currentPage, int size);
}
