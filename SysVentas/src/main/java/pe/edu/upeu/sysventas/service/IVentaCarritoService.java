package pe.edu.upeu.sysventas.service;

import pe.edu.upeu.sysventas.model.VentaCarrito;

import java.util.List;

public interface IVentaCarritoService extends  ICrudGenericService<VentaCarrito,Long>{
    List<VentaCarrito> listaCarritoCliente(String dni);
    void deleteCarAll(String dniruc);

}
