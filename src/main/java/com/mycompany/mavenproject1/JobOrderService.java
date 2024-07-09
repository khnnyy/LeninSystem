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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobOrderService {

  @Autowired
  private JobOrderRepository joVarRepository;

  public void updateJobOrderStatus(String jobCode, String newStatus) {
    joVarRepository.updateStatusByJobCode(jobCode, newStatus);
  }
}

