package com.spring.achareh.service.base;

import com.spring.achareh.model.base.BaseEntity;
import java.io.Serializable;
import java.util.List;


public interface BaseService<S extends BaseEntity<ID>, ID extends Serializable> {

    void save(S s);

    void delete(S s);

    void update(S s);

    S findById(ID id);

    List<S> findAll();
}
