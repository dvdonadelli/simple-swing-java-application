package br.com.dvdonadelli.dao;

import br.com.dvdonadelli.domain.Customer;

import java.util.*;

public class CustomerDaoImpl implements CustomerDao {

    private final Map<Long, Customer> customers = new HashMap<>();

    @Override
    public Boolean register(Customer customer) {
        if (customers.containsValue(customer))
            return false;

        customers.put(customer.getCpf(), customer);
        return true;
    }

    @Override
    public Customer get(Long cpf) {
        return customers.get(cpf);
    }

    @Override
    public void delete(Long cpf) {
        customers.remove(cpf);
    }

    @Override
    public void update(Customer customer) {
        Customer customerRegistered = customers.get(customer.getCpf());
        if (customerRegistered != null) {
            customerRegistered.setNome(customer.getNome());
            customerRegistered.setTel(customer.getTel());
            customerRegistered.setNumero(customer.getNumero());
            customerRegistered.setEnd(customer.getEnd());
            customerRegistered.setCidade(customer.getCidade());
            customerRegistered.setEstado(customer.getEstado());
        }
    }

    @Override
    public Collection<Customer> getAll() {
        return customers.values();
    }
}
