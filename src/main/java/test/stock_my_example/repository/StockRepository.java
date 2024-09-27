package test.stock_my_example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.stock_my_example.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
