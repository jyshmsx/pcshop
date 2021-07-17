package pers.jysh.pcshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 整个项目需要重构
 * 将前后端分离，将controller都从目前的页面跳转接口修改为api，从前端调取接口
 * 将DTO，VO类型分开
 * 对输入的表单数据的判断目前是通过javax.persistence.*进行判断，是否较为局限
 * 很多变量，文件命名没有按照“约定大于配置”的规则进行命名
 */
@SpringBootApplication
public class PcshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcshopApplication.class, args);
    }

}
