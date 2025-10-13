package pe.edu.upeu.sysventas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.model.CompraCarrito;
import pe.edu.upeu.sysventas.repository.CompraCarritoRepository;
import pe.edu.upeu.sysventas.repository.ICrudGenericRepository;
import pe.edu.upeu.sysventas.service.ICompCarritoService;

@RequiredArgsConstructor
@Service
public class CompCarritoServiceImp extends CrudGenericServiceImp<CompraCarrito,Long> implements ICompCarritoService {

    private final CompraCarritoRepository compCarritoRepository;

    @Override
    protected ICrudGenericRepository<CompraCarrito, Long> getRepo() {
        return compCarritoRepository;
    }
}
