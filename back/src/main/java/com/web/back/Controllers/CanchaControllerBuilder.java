package com.web.back.Controllers;

import com.web.back.Service.CanchaService;

public class CanchaControllerBuilder {
    private CanchaService canchaService;

    public CanchaControllerBuilder setCanchaService(CanchaService canchaService) {
        this.canchaService = canchaService;
        return this;
    }

    public CanchaController createCanchaController() {
        return new CanchaController(canchaService);
    }
}