package com.company.servicios;

import com.company.daos.IDao;
import com.company.entidades.Estudiante;

import java.util.List;

public class EstudianteService {

    //Implementamos la service, tiene mismos metodos pero le pasa la responsabilidad a dao
    private IDao<Estudiante> estudianteIDao;

    public IDao<Estudiante> getEstudianteServiceIDao() {
        return estudianteIDao;
    }

    public void setEstudianteServiceIDao(IDao<Estudiante> estudianteIDao) {
        this.estudianteIDao = estudianteIDao;
    }

    public Estudiante guardarEstudiante(Estudiante e){
        //delegamos responsabilidad de guardar al DAO
        return estudianteIDao.guardar(e);
    }
    public void eliminarEstudiante(Long id){
        estudianteIDao.eliminar(id);
    }
    public Estudiante buscarEstudiante(Long id){
        return estudianteIDao.buscar(id);
    }
    public List<Estudiante> buscarTodos(){
        return estudianteIDao.buscarTodos();
    }
}
