package satriiadaffa.crm.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import satriiadaffa.crm.api.models.CustomerModel;
import satriiadaffa.crm.api.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerModel> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<CustomerModel> getCustomerById(Long id){
        return customerRepository.findById(id);
    }

    public CustomerModel addCustomer(CustomerModel customerModel){
        return customerRepository.save(customerModel);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // ✅ Menambahkan Method Update
    public CustomerModel updateCustomer(Long id, CustomerModel newCustomerData) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setNIK(newCustomerData.getNIK());
                    existingCustomer.setName(newCustomerData.getName());
                    existingCustomer.setEmail(newCustomerData.getEmail());
                    existingCustomer.setPhone(newCustomerData.getPhone());
                    existingCustomer.setGender(newCustomerData.getGender());
                    existingCustomer.setAddress(newCustomerData.getAddress());
                    existingCustomer.setStatus(newCustomerData.getStatus());
                    return customerRepository.save(existingCustomer);
                }).orElseThrow(() -> new RuntimeException("Customer dengan ID " + id + " tidak ditemukan"));
    }
}
