package pe.edu.cibertec.rest_reportes_covisian.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.rest_reportes_covisian.model.bd.Memorandum;
import pe.edu.cibertec.rest_reportes_covisian.repository.MemorandumRepository;
import pe.edu.cibertec.rest_reportes_covisian.service.IMemorandumService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemorandumService implements IMemorandumService {
    private final MemorandumRepository memorandumRepository;

    @Override
    public Memorandum findMemorandumById(Integer id) {
        Memorandum memorandum = null;
        Optional<Memorandum> optional = memorandumRepository.findById(id);
        if(optional.isPresent())
            memorandum = optional.get();
        return memorandum;
    }

}
