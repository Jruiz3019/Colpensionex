package co.edu.uniquindio.repositorio.csv;

import java.util.List;

public interface IDao<ClaseEntidad, TipoId> {

    public List<ClaseEntidad> obtenerTodos();
}
