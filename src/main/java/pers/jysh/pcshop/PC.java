package pers.jysh.pcshop;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class PC {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @NotNull
    private String name;

    @ManyToMany(targetEntity = Accessory.class)
    private List<Accessory> accessories;

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
