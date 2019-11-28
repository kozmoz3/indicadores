package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;

@Service
public interface FPPTService {

	List<Object []> allByEstatus();
    Integer countBySector(List<Object[]> listEmision, String sector);
    List<String> listSector();
}
