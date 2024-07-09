/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author khanny
 */
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOrderRepository extends MongoRepository<JOVar, String> {

    // Method to update status by job code
    @Query("{'jobCode': ?0}")
    void updateStatusByJobCode(String jobCode, String status);
}
