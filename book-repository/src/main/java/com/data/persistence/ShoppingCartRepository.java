package com.data.persistence;

import com.domain.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Pc on 9/8/2017.
 */
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {


}
