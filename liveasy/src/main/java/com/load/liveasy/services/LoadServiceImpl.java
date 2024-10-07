package com.load.liveasy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.load.liveasy.entity.LoadData;
import com.load.liveasy.exception.DuplicateShipperIdException;
import com.load.liveasy.exception.ResourceNotFoundException;
import com.load.liveasy.repository.LoadRepo;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private LoadRepo loadRepo;

    @Override
    public void addLoad(LoadData data) {
        try {
            loadRepo.save(data);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateShipperIdException("Shipper ID " + data.getShipperId() + " already exists.");
        }

    }

    @Override
    public List<LoadData> getAllLoads() {
        return loadRepo.findAll();
    }

    @Override
    public List<LoadData> getAllById(String shipperId) {

        return loadRepo.findAllByShipperId(shipperId);

    }

    @Override
    public LoadData getLoadById(int loadId) {

        return loadRepo.findById(loadId).orElseThrow(() -> new ResourceNotFoundException("Load does not exists"));
    }

    @Override
    public LoadData updateLoadById(LoadData data, int loadId) {
        var data1 = loadRepo.findById(loadId).orElseThrow(() -> new ResourceNotFoundException("Load does not exists"));
        if (data1 != null) {
            data1.setLoadingPoint(data.getLoadingPoint());
            data1.setUnloadingPoint(data.getUnloadingPoint());
            data1.setProductType(data.getProductType());
            data1.setNoOfTrucks(data.getNoOfTrucks());
            data1.setWeight(data.getWeight());
            data1.setComment(data.getComment());
            data1.setDate(data.getDate());
            loadRepo.save(data1);
        }
        return data1;
    }

    @Override
    public void deleteLoadById(int loadId) {
        loadRepo.deleteById(loadId);
    }

}
