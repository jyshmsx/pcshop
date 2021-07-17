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

    // XXX 是在变量名上进行自动注入好还是使用构造函数自动装配好？
    @Autowired
    public DesignPCController(AccessoryRepository accessoryRepository,
                              PCRepository designRepo){
        this.accessoryRepository = accessoryRepository;
        this.designRepo = designRepo;
    }


    /**
     * 依据不同类型，将accessory的所有值加入model中
     * @param model
     * @return
     */
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

    /**
     * 将order添加到model上
     * @return
     */
    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    /**
     * 将pc添加到model上
     * @return
     */
    @ModelAttribute(name = "pc")
    public PC pc(){
        return new PC();
    }

    /**
     * 将/design页面返回的表单值，保存至数据库，加入至model中，并跳转至/orders/current
     * @param design
     * @param errors
     * @param order
     * @return
     */
    @PostMapping
    public String processDesign(@Valid PC design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }

        PC saved = designRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }

    /**
     * 获取某一个类型的accessory
     * @param accessory
     * @param type
     * @return
     */
    private List<Accessory> filterByType(List<Accessory> accessory, Type type){
        return accessory.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

}
