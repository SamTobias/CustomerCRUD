package br.com.samueltobias.customercrud.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import br.com.samueltobias.customercrud.asynctask.Callback;
import br.com.samueltobias.customercrud.asynctask.FetchCustomersTask;
import br.com.samueltobias.customercrud.database.AppDatabase;
import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryTest {

    @Test
    public void fetch() {
        final List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("PDV A", "99999-9999"));
        customers.add(new Customer("PDV B", "99999-9999"));

        CustomerDao customerDaoMock = Mockito.mock(CustomerDao.class);
        FetchCustomersTask taskMock = Mockito.mock(FetchCustomersTask.class);

        Mockito.doReturn(null).when(taskMock).execute();
        CustomerRepository customerRepository = Mockito.spy(new CustomerRepository(customerDaoMock));

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Callback<List<Customer>> argument = invocation.getArgument(0);
                argument.onFinish(customers);
                return null;
            }
        }).when(customerRepository).getCustomers(any(Callback.class));

        Mockito.doReturn(customers).when(customerDaoMock).getAll();

        customerRepository.getCustomers(new Callback<List<Customer>>() {
            @Override
            public void onFinish(List<Customer> list) {
                Assert.assertEquals(customers.size(), list.size());
            }
        });
    }
}