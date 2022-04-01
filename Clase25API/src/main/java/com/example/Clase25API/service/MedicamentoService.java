package com.example.Clase25API.service;

import com.example.Clase25API.Dao.IDao;
import com.example.Clase25API.model.Medicamento;

public class MedicamentoService {
private IDao<Medicamento> medicamentoIDao;

public MedicamentoService(IDao<Medicamento> medicamentoIDao){
    this.medicamentoIDao = medicamentoIDao;
}

public Medicamento guardar(Medicamento medicamento){
    return medicamentoIDao.guardar(medicamento);
}
public void eliminar(int id){
    medicamentoIDao.eliminar(id);
}
public Medicamento modificar(Medicamento m){
    return medicamentoIDao.modificar(m);
}

public  Medicamento buscar(int id){
    return  medicamentoIDao.buscar(id);
}
}
