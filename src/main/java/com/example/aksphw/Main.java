package com.example.aksphw;

import com.example.aksphw.product.controler.Shop;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Main {


    private Shop shop;

    public Main(Shop shop) {
        this.shop = shop;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listProducts(){
        shop.get();
    }
}
