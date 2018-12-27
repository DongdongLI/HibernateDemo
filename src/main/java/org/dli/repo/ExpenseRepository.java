package org.dli.repo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.dli.model.Expense;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.CrudRepository;

@EntityScan("org.dli.repo.AccountTransactionRepository")
public interface ExpenseRepository extends CrudRepository{
	@Transactional
	void deleteByDate(Date date);
	
	List<Expense> findAllByDate(Date date);
}
