package org.twino.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.twino.model.LoanApplication;

import java.util.Optional;

@Repository
public interface LoanApplicationRepository extends PagingAndSortingRepository<LoanApplication, Long> {

    @Override
    Iterable<LoanApplication> findAll(Sort sort);

    @Override
    Page<LoanApplication> findAll(Pageable pageable);

    @Override
    <S extends LoanApplication> S save(S s);

    @Override
    <S extends LoanApplication> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<LoanApplication> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    Iterable<LoanApplication> findAll();

    @Override
    Iterable<LoanApplication> findAllById(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(LoanApplication loanApplication);

    @Override
    void deleteAll(Iterable<? extends LoanApplication> iterable);

    @Override
    void deleteAll();
}
