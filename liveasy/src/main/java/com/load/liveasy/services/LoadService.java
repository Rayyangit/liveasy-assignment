package com.load.liveasy.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.load.liveasy.entity.LoadData;

@Service
public interface LoadService {

    public void addLoad(LoadData data);

    public List<LoadData> getAllLoads();

    public List<LoadData> getAllById(String shipperId);

    public LoadData getLoadById(int loadId);

    public LoadData updateLoadById(LoadData data, int loadId);

    public void deleteLoadById(int loadId);

}
