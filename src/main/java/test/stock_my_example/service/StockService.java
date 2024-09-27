package test.stock_my_example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import test.stock_my_example.domain.Stock;
import test.stock_my_example.repository.StockRepository;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * 부모(NamedLockStockFacade)의 트랜잭션과 별도의 트랜잭션에서 실행되어야 하기에
     * propagation = Propagation.REQUIRES_NEW로 설정
     * 그리고 같은 dataSource을 사용할 것이기에 커넥션풀 사이즈를 변경해준다.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized void decrease(Long id, Long quantity) {
        // 재고 조회
        // 재고를 감소시킨뒤
        // 갱신된 값을 저장
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
