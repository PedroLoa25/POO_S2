package pe.edu.upeu.sysventas.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.model.VentaCarrito;
import pe.edu.upeu.sysventas.repository.ICrudGenericRepository;
import pe.edu.upeu.sysventas.repository.VentaCarritoRepository;
import pe.edu.upeu.sysventas.service.IVentCarritoService;
@RequiredArgsConstructor
@Service
public class VentCarritoServiceImp extends CrudGenericServiceImp<VentaCarrito, Long> implements IVentCarritoService {

    private final VentaCarritoRepository carritoRepository;

    @Override
    protected ICrudGenericRepository<VentaCarrito, Long> getRepo() {
        return carritoRepository;
    }
}
