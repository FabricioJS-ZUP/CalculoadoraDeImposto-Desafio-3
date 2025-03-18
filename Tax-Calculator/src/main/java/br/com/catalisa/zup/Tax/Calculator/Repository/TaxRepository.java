package br.com.catalisa.zup.Tax.Calculator.Repository;

import br.com.catalisa.zup.Tax.Calculator.Models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaxReposirtory extends JpaRepository<Tax, Long> {
}