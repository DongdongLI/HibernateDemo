package org.dli.repo;

import java.util.Date;
import java.util.List;

import org.dli.model.Income;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@EntityScan("org.dli.repo.AccountTransactionRepository")
public interface IncomeRepository extends CrudRepository<Income, Date>{
	@Transactional
	void deleteByDate(Date date);
	
	List<Income> findAllByDate(Date date);
}
