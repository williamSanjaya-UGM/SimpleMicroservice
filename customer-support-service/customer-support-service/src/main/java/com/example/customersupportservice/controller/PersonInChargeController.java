package com.example.customersupportservice.controller;

import com.example.customersupportservice.entity.CustomerDetail;
import com.example.customersupportservice.entity.PersonInCharge;
import com.example.customersupportservice.service.PersonInChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.Optional;

@RestController
@RequestMapping("/customer-support")
public class PersonInChargeController {

    @Autowired
    private PersonInChargeService personInChargeService;

    @PostMapping("/{personId}")
    public PersonInCharge addFeedback(@RequestBody CustomerDetail customerDetail,
                                      @PathVariable String personId) {
        return personInChargeService.addFeedBack(personId, customerDetail);
    }

    @GetMapping("/{personId}")
    public Optional<PersonInCharge> getFeedbackById(@PathVariable String personId){
        return personInChargeService.getFeedbackById(personId);
    }

    @PutMapping("/{personId}")
    public CustomerDetail editFeedback(@PathVariable String userId,
                                       @RequestBody CustomerDetail customerDetailRequest) {
        return personInChargeService.editFeedback(userId, customerDetailRequest);
    }

    @DeleteMapping("/{personId}/pic/{userId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable String personId, @PathVariable String userId){
        return personInChargeService.deleteFeedback(userId, personId);
    }
}
