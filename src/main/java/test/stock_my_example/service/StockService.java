package test.stock_my_example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.stock_my_example.domain.Stock;
import test.stock_my_example.repository.StockRepository;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public synchronized void decrease(Long id, Long quantity) {
        // 재고 조회
        // 재고를 감소시킨뒤
        // 갱신된 값을 저장
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
