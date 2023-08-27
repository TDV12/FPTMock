package fa.edu.vn.testCase;

import fa.edu.vn.entites.ClassBatch;
import fa.edu.vn.service.IClassBatchService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class TestFunctionFindUsingSpecification {

    @Autowired
    private IClassBatchService classBatchService;

    @Test
    public void functionTest() {
        Page<ClassBatch> pageClassByCondition = classBatchService.findPageClassByCondition(1, 1, "", "", "", PageRequest.of(0, 1));
        System.out.println(pageClassByCondition.getContent());

    }

}
