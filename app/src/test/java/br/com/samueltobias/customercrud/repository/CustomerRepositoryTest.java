package br.com.samueltobias.customercrud.repository;

import android.os.Build;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import br.com.samueltobias.customercrud.asynctask.Callback;
import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

@Config(sdk = Build.VERSION_CODES.P)
@RunWith(RobolectricTestRunner.class)
public class CustomerRepositoryTest {

    @Mock
    private CustomerDao mockDao;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUpMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDownMockito() {
        Mockito.validateMockitoUsage();
    }

    @Test
    public void deveRetornarQuantidadeCorreta() {
//        final List<Customer> customers = new ArrayList<>();
//        customers.add(new Customer("PDV A", "99999-9999"));
//        customers.add(new Customer("PDV B", "99999-9999"));
//
//        Mockito.when(mockDao.getAll()).thenReturn(customers);
//        Mockito.when(mockDao.getAll()).thenReturn(customers);
//
//        CustomerRepository repository =  new CustomerRepository(mockDao);
//        final Object syncObject = new Object();
//
//        repository.getCustomers(new Callback<List<Customer>>() {
//            @Override
//            public void onFinish(List<Customer> list) {
//                Assert.assertEquals(customers.size(), list.size());
//                synchronized (syncObject) {
//                    syncObject.notify();
//                }
//            }
//        });
//
//        synchronized (syncObject) {
//            try {
//                syncObject.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}