package com.longyx.order.repository;

import com.longyx.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mr.Longyx
 * @date 2020年01月13日 23:31
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}
