package pe.edu.upeu.sysventas.service;

import pe.edu.upeu.sysventas.dto.MenuMenuItemDto;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface IMenuMenuItemDto {
    List<MenuMenuItemDto> listaAccesos(String perfil, Properties idioma);//libreria no confirmada
    Map<String, String[]> accesosAutorizados(List<MenuMenuItemDto> accesos);
}
