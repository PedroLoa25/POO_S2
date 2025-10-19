package pe.edu.upeu.sysventas.service;

import pe.edu.upeu.sysventas.dto.ModeloDataAutocomplete;
import pe.edu.upeu.sysventas.model.Cliente;

import java.util.List;

public interface IClienteService extends ICrudGenericService<Cliente,String> {
    List<ModeloDataAutocomplete> listAutoCompletCliente();
}
