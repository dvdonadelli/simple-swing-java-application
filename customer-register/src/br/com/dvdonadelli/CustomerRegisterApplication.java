package br.com.dvdonadelli;

import br.com.dvdonadelli.dao.CustomerDao;
import br.com.dvdonadelli.dao.CustomerDaoImpl;
import br.com.dvdonadelli.domain.Customer;

import javax.swing.*;

import static br.com.dvdonadelli.commons.Constants.*;

public class CustomerRegisterApplication {

    private static CustomerDao customer;

    public static void main(String[] args) {
        customer = new CustomerDaoImpl();

        String option = JOptionPane.showInputDialog(null,
                OPTIONS,
                REGISTER_TITLE,
                JOptionPane.INFORMATION_MESSAGE);

        while (!isValidOption(option)) {
            if ("".equals(option)) {
                exit();
            }
            option = JOptionPane.showInputDialog(null,
                    INVALID_OPTION,
                    INVALID_OPTION_TITLE,
                    JOptionPane.INFORMATION_MESSAGE);
        }

        while (isValidOption(option)) {
            if (isExitOption(option)) {
                exit();
            } else if (isRegisterOption(option)) {
                String dados = JOptionPane.showInputDialog(null,
                        REGISTER_OR_UPDATE_MESSAGE,
                        REGISTER_TITLE,
                        JOptionPane.INFORMATION_MESSAGE);
                register(dados);
            } else if (isGetOption(option)) {
                String cpf = JOptionPane.showInputDialog(null,
                        GET_OR_DELETE_MESSAGE,
                        GET_TITLE,
                        JOptionPane.INFORMATION_MESSAGE);

                get(cpf);
            } else if (isDeleteOption(option)) {
                String cpf = JOptionPane.showInputDialog(null,
                        GET_OR_DELETE_MESSAGE,
                        DELETE_TITLE,
                        JOptionPane.INFORMATION_MESSAGE);
                delete(cpf);
            } else {
                String data = JOptionPane.showInputDialog(null,
                        REGISTER_OR_UPDATE_MESSAGE,
                        UPDATE_TITLE,
                        JOptionPane.INFORMATION_MESSAGE);
                update(data);
            }

            option = JOptionPane.showInputDialog(null,
                    OPTIONS,
                    REGISTER_TITLE,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isDeleteOption(String o) {
        int option = Integer.parseInt(o);

        return option == DELETE;
    }

    private static boolean isValidOption(String o) {
        int option = Integer.parseInt(o);

        return option >= REGISTER && option <= EXIT;
    }

    private static boolean isExitOption(String o) {
        int option = Integer.parseInt(o);

        return option == EXIT;
    }

    private static boolean isRegisterOption(String o) {
        int option = Integer.parseInt(o);

        return option == REGISTER;
    }

    private static boolean isGetOption(String o) {
        int option = Integer.parseInt(o);

        return option == GET;
    }

    private static void get(String cpf) {
        Customer c = customer.get(Long.parseLong(cpf));
        if (c != null) {
            JOptionPane.showMessageDialog(null,
                    "Cliente encontrado com sucesso: " + c,
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Cliente não encontrado",
                    "ERRO",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void register(String data) {
        String[] split = data.split(",");
        Customer c = new Customer(split[0].trim(),
                Long.parseLong(split[1].trim()),
                Long.parseLong(split[2].trim()),
                split[3].trim(),
                Integer.parseInt(split[4].trim()),
                split[5].trim(),
                split[6].trim());
        Boolean isCadastrado = customer.register(c);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null,
                    "Cliente cadastrado com sucesso ",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Cliente já se encontra cadastrado",
                    "Erro",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void update(String data) {
        String[] split = data.split(",");
        Customer c = new Customer(split[0].trim(),
                Long.parseLong(split[1].trim()),
                Long.parseLong(split[2].trim()),
                split[3].trim(),
                Integer.parseInt(split[4].trim()),
                split[5].trim(),
                split[6].trim());
        customer.update(c);
    }

    private static void delete(String cpf) {
        customer.delete(Long.parseLong(cpf));
        JOptionPane.showMessageDialog(null,
                "Cliente excluído com sucesso: ",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void exit() {
        StringBuilder customersRegistered = new StringBuilder();
        for (Customer customer : customer.getAll()) {
            customersRegistered.append(customer.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null,
                "Clientes cadastrados: " + customersRegistered,
                "Até logo",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

}
