package com.spring.achareh.service.base;

import com.spring.achareh.model.base.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface BaseService<S extends BaseEntity<ID>, ID extends Serializable> {

    void save(S s);

    void delete(S s);

    void update(S s);

    Optional<S> findById(ID id);

    List<S> findAll();
}
