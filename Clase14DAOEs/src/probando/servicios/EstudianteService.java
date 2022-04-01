package probando.servicios;

import probando.daos.IDao;
import probando.entidades.Estudiante;

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
        //delegamos responsibility de guardar al DAO
        estudianteIDao.guardar(e);
        return null;
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