package pe.edu.cibertec.rest_reportes_covisian.service;

import pe.edu.cibertec.rest_reportes_covisian.model.bd.Memorandum;

public interface IMemorandumService {
    Memorandum findMemorandumById(Integer id);
}
