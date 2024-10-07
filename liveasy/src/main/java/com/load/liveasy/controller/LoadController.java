package com.load.liveasy.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.load.liveasy.entity.LoadData;
import com.load.liveasy.services.LoadService;

@RestController
public class LoadController {

    @Autowired
    private LoadService loadService;

    @PostMapping("/load")
    public String saveLoad(@RequestBody LoadData data) {
        String shipperIdStr = data.getShipperId();

        if (shipperIdStr.startsWith("shipper:")) {
            shipperIdStr = shipperIdStr.substring(8);
        }
        UUID shipperId = UUID.fromString(shipperIdStr);
        data.setShipperId(shipperId.toString());
        loadService.addLoad(data);
        return "loads details added successfully ";
    }

    @GetMapping("/load")
    public List<LoadData> getLoad() {
        return loadService.getAllLoads();
    }

    @RequestMapping("/load/ship")
    public List<LoadData> getAllByShipper(@RequestParam String shipperId) {
        return loadService.getAllById(shipperId);
    }

    @GetMapping("/load/{loadId}")
    public LoadData getLoadById(@PathVariable int loadId) {
        return loadService.getLoadById(loadId);
    }

    @PutMapping("load/{loadId}")
    public LoadData updateLoad(@PathVariable int loadId, @RequestBody LoadData data) {

        return loadService.updateLoadById(data, loadId);

    }

    @DeleteMapping("load/{loadId}")
    public String deleteLoad(@PathVariable int loadId) {
        loadService.deleteLoadById(loadId);
        return "Record deleted successfully!";

    }

}
