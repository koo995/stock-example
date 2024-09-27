package test.stock_my_example.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.stock_my_example.domain.Stock;
import test.stock_my_example.repository.StockRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void before() {
        stockRepository.saveAndFlush(new Stock(1L, 100L));
    }

    @AfterEach
    public void after() {
        stockRepository.deleteAll();
    }

    @DisplayName("재고 감소")
    @Test
    void decrease() throws Exception {
        // given
        Long id = 1L;
        Long quantity = 1L;

        // when
        stockService.decrease(id, quantity);

        // then
        Stock stock = stockRepository.findById(id).orElseThrow();
        assertThat(stock.getQuantity()).isEqualTo(99L);
    }






}