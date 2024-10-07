package com.load.liveasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.load.liveasy.entity.LoadData;

@Repository
public interface LoadRepo extends JpaRepository<LoadData, Integer> {

    List<LoadData> findAllByShipperId(String shipperId);

}
