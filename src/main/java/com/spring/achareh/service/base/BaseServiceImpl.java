package com.spring.achareh.service.base;

import com.spring.achareh.model.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;


public abstract class BaseServiceImpl<S extends BaseEntity<ID>, ID extends Serializable,R extends JpaRepository<S,ID>>
        implements BaseService<S, ID> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void save(S s) {
        repository.save(s);
    }

    @Transactional
    @Override
    public void delete(S s) {
    }

    @Transactional
    @Override
    public void update(S s) {

    }

    @Override
    public S findById(ID id) {
        return null;
    }

    @Override
    public List<S> findAll() {
        return null;
    }
}
