package pe.edu.upeu.sysventas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.sysventas.model.VentaCarrito;

import java.util.List;

public interface VentaCarritoRepository extends ICrudGenericRepository<VentaCarrito, Long> {

    @Query(value = "SELECT c.* FROM upeu_venta_carrito c WHERE c.dniruc=:dniruc", nativeQuery = true)
    List<VentaCarrito> listaCarritoCliente(@Param("dniruc") String dniruc);

    void deleteByDniruc(String dniruc);
}
