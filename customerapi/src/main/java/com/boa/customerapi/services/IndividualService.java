package com.boa.customerapi.services;

import com.boa.customerapi.models.Individual;
import com.boa.customerapi.repositories.IndividualRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualService {
    @Autowired
    private IndividualRepo individualRepo;

    //add

    public Individual addIndividual(Individual individual){
        return this.individualRepo.save(individual);
    }

    //getall

    public List<Individual> getAllIndividuals(){
        return  this.individualRepo.findAll();
    }

    //get by id

    public Individual getIndividualById(long customerId){
        return this.individualRepo.findById(customerId).orElse(null);
    }

    //update
    public Individual updateIndividualByCustomerId(long customerId, String email){
        Individual individual=this.getIndividualById(customerId);

        if(individual!=null){
            individual.setEmail(email);
            return this.individualRepo.save(individual);
        }
        else
            return null;
    }


    //delete


    public boolean deleteIndividualByCustomerId(long customerId){
        boolean status=false;
        Individual individual=this.getIndividualById(customerId);

        if(individual!=null){
            this.individualRepo.deleteById(customerId);
            
            status=true;

            }
       return status;
    }




}
