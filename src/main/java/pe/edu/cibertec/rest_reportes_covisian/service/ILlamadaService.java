package pe.edu.cibertec.rest_reportes_covisian.service;

import pe.edu.cibertec.rest_reportes_covisian.model.bd.Llamada;

public interface ILlamadaService {
    Llamada llamadaPorOrden(int orden);

}
