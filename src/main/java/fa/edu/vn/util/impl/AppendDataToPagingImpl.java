package fa.edu.vn.util.impl;

import fa.edu.vn.util.IAppendDataToPaging;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class AppendDataToPagingImpl implements IAppendDataToPaging {

    @Override
    public void appendDataToPaging(Model model, int totalPage, int currentPage, int size) {
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sizePage", size);
    }
}
