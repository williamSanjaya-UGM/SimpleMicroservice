package com.example.customersupportservice.service;

import com.example.customersupportservice.entity.CustomerDetail;
import com.example.customersupportservice.entity.PersonInCharge;
import com.example.customersupportservice.exception.ResourceNotFoundException;
import com.example.customersupportservice.repository.CustomerDetailRepository;
import com.example.customersupportservice.repository.PersonInChargeRepository;
import com.example.customersupportservice.service.dto.PICDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonInChargeService {

    @Autowired
    private CustomerDetailRepository customerDetailRepository;

    @Autowired
    private PersonInChargeRepository personInChargeRepository;

    @Autowired
    private RestTemplate restTemplate;

    public PersonInCharge addPersonInCharge(CustomerDetail customerDetail, String personId) {
        Set<CustomerDetail> customerDetails =new HashSet<>();
        customerDetails.add(customerDetail);

        PersonInCharge personInCharge = new PersonInCharge();
        boolean present = personInChargeRepository.findByPersonId(personId).isPresent();
        if(!present) {
            PICDto picDto = restTemplate.getForObject("http://admin-dummy/" + personId, PICDto.class);
            personInCharge.setPersonId(picDto.getPersonId());
            personInCharge.setPersonInChargeName(picDto.getPersonName());
            personInCharge.setCustomerDetails(customerDetails);
            return personInChargeRepository.save(personInCharge);
        }

        return personInChargeRepository.findByPersonId(personId).map(personInCharge1 -> {
            personInCharge1.getCustomerDetails().add(customerDetail);
            return personInChargeRepository.save(personInCharge1);
        }).orElseThrow(()->new ResourceNotFoundException("There is trouble in saving person feedback message"));
    }

    public PersonInCharge addFeedBack(String personId, CustomerDetail customerDetail) {
        boolean present = customerDetailRepository.findById(customerDetail.getUserId()).isPresent();
        if(! present) {
            customerDetailRepository.save(customerDetail);
            return addPersonInCharge(customerDetail, personId);
        } else
            throw new RuntimeException("Sorry duplicate userId");
    }

    public Optional<PersonInCharge> getFeedbackById(String personId){
        return personInChargeRepository.findByPersonId(personId);
    }

    public CustomerDetail editFeedback(String userId, CustomerDetail customerDetailRequest) {
        return customerDetailRepository.findById(userId).map(customerDetail -> {
            customerDetail.setFeedback_message(customerDetailRequest.getFeedback_message());
            return customerDetailRepository.save(customerDetail);
        }).orElseThrow(()->new ResourceNotFoundException("customer userId not found"));
    }

    public ResponseEntity<?> deleteFeedback(String userId, String personId){
        customerDetailRepository.findById(userId).map(customerDetail -> {
            customerDetailRepository.delete(customerDetail);
            return null;
        });
        personInChargeRepository.findByPersonId(personId).map(personInCharge -> {
            CustomerDetail customerDetail = personInCharge
                    .getCustomerDetails()
                    .stream()
                    .filter(person -> person.getUserId().equals(userId))
                    .findAny()
                    .get();
            personInCharge.getCustomerDetails().remove(customerDetail);
            return personInChargeRepository.save(personInCharge);
        });
        return ResponseEntity.ok().build();
    }
}
