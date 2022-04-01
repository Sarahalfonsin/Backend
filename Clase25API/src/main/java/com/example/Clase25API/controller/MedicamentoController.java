package com.example.Clase25API.controller;

import com.example.Clase25API.Dao.MedicamentoDAOH2;
import com.example.Clase25API.model.Medicamento;
import com.example.Clase25API.service.MedicamentoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    //instanciar service
    private MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDAOH2());

    //Espera una peticion post
    @PostMapping("/registrar")
    //recibimos una peticion y esta llega por el body este gurda
    public Medicamento guardar(@RequestBody Medicamento medicamento){
        return  medicamentoService.guardar(medicamento);
    }
    @PutMapping("/actualizar")
    public  ResponseEntity<Medicamento> modificar(@RequestBody Medicamento medicamento){
        ResponseEntity response = null;
        //si no encuentra
        if(medicamentoService.buscar(medicamento.getId()) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(medicamentoService.modificar(medicamento),HttpStatus.OK);
        }
        return  response;
    }

    //espera una get, ese ide se guarda en la variable
    //para eso le pongo el path variable este trae
    @GetMapping("/{id}")
    public  Medicamento buscar(@PathVariable("id") int id){
        return  medicamentoService.buscar(id);
    }

//objeto que nos devuelva un codigo de status si la entidad no
//esta en la base de datos y otor codigo si se elimino

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable int id){
        ResponseEntity response = null;

        //si no encuentra
        if(medicamentoService.buscar(id)==null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
          medicamentoService.eliminar(id);
          response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return  response;
    }

}
