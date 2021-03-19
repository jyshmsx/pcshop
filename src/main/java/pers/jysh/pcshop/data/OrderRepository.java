package pers.jysh.pcshop.data;

import org.springframework.data.repository.CrudRepository;
import pers.jysh.pcshop.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
