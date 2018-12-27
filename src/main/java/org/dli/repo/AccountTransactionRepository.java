package org.dli.repo;

import org.dli.model.AccountTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories(basePackages="org.dli.repo", entityManagerFactoryRef="entityManagerFactory")
//@NoRepositoryBean
public interface AccountTransactionRepository extends CrudRepository<Double, String>{
	@Query(value = "select sum(amount) from account_transaction atn "
			+ "where atn.account_type = :account_type", nativeQuery = true)
	Double findTotalByAccountType(@Param("account_type") String account_type);
}
