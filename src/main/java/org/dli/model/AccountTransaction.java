package org.dli.model;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.data.repository.NoRepositoryBean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type",discriminatorType=DiscriminatorType.STRING)
public abstract class AccountTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	protected Date date;
	protected String description;
	protected Double amount;
}
