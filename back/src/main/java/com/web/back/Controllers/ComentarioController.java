package com.web.back.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.back.Service.ComentarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping({"/comentario"})
public class ComentarioController {
    @Autowired
    ComentarioService comentarioService;
}
