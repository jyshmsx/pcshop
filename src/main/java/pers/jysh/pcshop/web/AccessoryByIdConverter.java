package pers.jysh.pcshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pers.jysh.pcshop.Accessory;
import pers.jysh.pcshop.data.AccessoryRepository;

import java.util.Optional;

@Component
public class AccessoryByIdConverter implements Converter<String, Accessory> {

    private AccessoryRepository accessoryRepo;

    @Autowired
    public AccessoryByIdConverter(AccessoryRepository accessoryRepo){
        this.accessoryRepo = accessoryRepo;
    }

    @Override
    public Accessory convert(String id){
        Optional<Accessory> optionalAccessory = accessoryRepo.findById(id);
        return optionalAccessory.isPresent() ?
                optionalAccessory.get() : null;
    }
}
