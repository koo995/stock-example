package test.stock_my_example.facade;

import org.springframework.stereotype.Component;
import test.stock_my_example.service.OptimisticLockStockService;

/**
 * 실패했을 때, 재시도를 해야하기에 필요하다.
 */
@Component
public class OptimisticLockStockFacade {
    private final OptimisticLockStockService optimisticLockStockService;

    public OptimisticLockStockFacade(OptimisticLockStockService optimisticLockStockService) {
        this.optimisticLockStockService = optimisticLockStockService;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException {
        while (true) {
            try {
                optimisticLockStockService.decrease(id, quantity);
                break;
            } catch (Exception e) {
                // 재시도
                Thread.sleep(50);
            }
        }
    }
}
