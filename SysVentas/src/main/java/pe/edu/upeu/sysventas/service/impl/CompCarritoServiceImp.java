package pe.edu.upeu.sysventas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.model.CompraCarrito;
import pe.edu.upeu.sysventas.repository.CompraCarritoRepository;
import pe.edu.upeu.sysventas.repository.ICrudGenericRepository;
import pe.edu.upeu.sysventas.service.ICompraCarritoService;

@RequiredArgsConstructor
@Service
public class CompCarritoServiceImp extends CrudGenericServiceImp<CompraCarrito,Long> implements ICompraCarritoService {

    private final CompraCarritoRepository compCarritoRepository;

    @Override
    protected ICrudGenericRepository<CompraCarrito, Long> getRepo() {
        return compCarritoRepository;
    }
}
