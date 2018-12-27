package org.dli.main;

import java.text.SimpleDateFormat;
import java.util.List;

import org.dli.model.Expense;
import org.dli.model.Income;
import org.dli.repo.AccountTransactionRepository;
import org.dli.repo.ExpenseRepository;
import org.dli.repo.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@EnableJpaRepositories("org.dli.repo")
@SpringBootApplication
public class GradleHibernateJapApplication implements CommandLineRunner{

	
	@Autowired
	AccountTransactionRepository atr;
	
	@Autowired
	IncomeRepository ir;
	
	
	@Autowired
	ExpenseRepository er;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public void run(String... args) throws Exception {
		// save some data first.
		ir.save(new Income(formatter.parse("01/01/2018"), "first income", 1000.0));
		ir.save(new Income(formatter.parse("02/01/2018"), "second income", 2000.0));
		ir.save(new Income(formatter.parse("03/01/2018"), "third income", 2000.0));

		er.save(new Expense(formatter.parse("01/01/2018"), "first expense", 500.0));
		er.save(new Expense(formatter.parse("02/01/2018"), "second expense", 750.0));
		er.save(new Expense(formatter.parse("03/01/2018"), "third expense", 750.0));
		
		// delete 01/02 data
		ir.deleteByDate(formatter.parse("02/01/2018"));
		er.deleteByDate(formatter.parse("02/01/2018"));
		
		// update
		List<Income> incomes = ir.findAllByDate(formatter.parse("03/01/2018"));
		incomes.stream().forEach(income -> {
			income.setAmount(500.0);
			ir.save(income);
		});
		
		Iterable<Expense> expenses = er.findAllByDate(formatter.parse("03/01/2018"));
		expenses.forEach(expense -> {
			expense.setAmount(250.0);
			er.save(expense);
		});
		
		Double balance = atr.findTotalByAccountType("income") - atr.findTotalByAccountType("expense");
		System.out.println(balance);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(GradleHibernateJapApplication.class, args);
	}
}
