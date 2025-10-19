package pe.edu.upeu.sysventas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.dto.ModeloDataAutocomplete;
import pe.edu.upeu.sysventas.model.Producto;
import pe.edu.upeu.sysventas.repository.ProductoRepository;
import pe.edu.upeu.sysventas.service.ProductoIService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImp implements ProductoIService {

    private static final Logger logger = LoggerFactory.getLogger(ProductoServiceImp.class);

    @Autowired
    ProductoRepository pRepo;

    @Override
    public Producto save(Producto producto) {
        return pRepo.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        return pRepo.findAll();
    }

    @Override
    public Producto update(Producto producto) {
        return pRepo.save(producto);
    }

    @Override
    public void delete(Long id) {
        pRepo.deleteById(id);
    }

    @Override
    public Producto findById(Long id) {
        return pRepo.findById(id).orElse(null);
    }

    @Override
    public List<ModeloDataAutocomplete> listAutoCompletProducto(String nombre) {
        List<ModeloDataAutocomplete> listarProducto = new ArrayList<>();
        try {
            for (Producto producto : pRepo.listAutoCompletProducto(nombre + "%")) {
                ModeloDataAutocomplete data = new ModeloDataAutocomplete();
                data.setIdx(producto.getNombre());

                data.setNameDysplay(String.valueOf(producto.getIdProducto()));
                data.setOtherData(producto.getPu() + ":" + producto.getStock());
                listarProducto.add(data);
            }
        } catch (Exception e) {
            logger.error("Error al realizar la busqueda", e);
        }
        return listarProducto;
    }

    @Override
    public List<ModeloDataAutocomplete> listAutoCompletProducto() {
        List<ModeloDataAutocomplete> listarProducto = new ArrayList<>();
        try {
            for (Producto producto : pRepo.findAll())
            {
                ModeloDataAutocomplete data = new ModeloDataAutocomplete();
                data.setIdx(String.valueOf(producto.getIdProducto()));
                data.setNameDysplay(producto.getNombre());
                data.setOtherData(producto.getPu() + ":" + producto.getStock());
                listarProducto.add(data);
            }
        } catch (Exception e) {
            logger.error("Error al realizar la busqueda", e);
        }
        return listarProducto;
    }
}
