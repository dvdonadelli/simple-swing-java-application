package br.com.dvdonadelli.dao;

import br.com.dvdonadelli.domain.Customer;

import java.util.Collection;

public interface CustomerDao {

    public Boolean register(Customer customer);

    public Customer get(Long cpf);

    public void delete(Long cpf);

    public void update(Customer customer);

    public Collection<Customer> getAll();

}
