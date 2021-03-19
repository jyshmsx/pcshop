package pers.jysh.pcshop.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.jysh.pcshop.Accessory;
import pers.jysh.pcshop.Accessory.Type;
import pers.jysh.pcshop.Order;
import pers.jysh.pcshop.PC;
import pers.jysh.pcshop.data.AccessoryRepository;
import pers.jysh.pcshop.data.PCRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignPCController {

    private final AccessoryRepository accessoryRepository;

    private PCRepository designRepo;

    @Autowired
    public DesignPCController(AccessoryRepository accessoryRepository,
                              PCRepository designRepo){
        this.accessoryRepository = accessoryRepository;
        this.designRepo = designRepo;
    }

    @GetMapping
    public String showSelectPcForm(Model model){
        List<Accessory> accessories = new ArrayList<>();
        accessoryRepository.findAll().forEach(i -> accessories.add(i));

        Type[] types = Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(accessories, type));
        }
        return "design";
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "pc")
    public PC pc(){
        return new PC();
    }

    @PostMapping
    public String processDesign(@Valid PC design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }

        PC saved = designRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }

    private List<Accessory> filterByType(List<Accessory> accessory, Type type){
        return accessory.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

}
