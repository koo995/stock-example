package test.stock_my_example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.stock_my_example.domain.Stock;
import test.stock_my_example.repository.StockRepository;

@Service
public class OptimisticLockStockService {
    private final StockRepository stockRepository;

    public OptimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findByIdWithOptimisticLock(id);
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
