package pe.edu.upeu.sysventas.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.dto.MenuMenuItemDto;
import pe.edu.upeu.sysventas.components.ViewNavigator;
import pe.edu.upeu.sysventas.service.IMenuMenuItemDao;

import java.util.*;

@Service
public class MenuMenuItemDaoImp implements IMenuMenuItemDao {
    @Override
    public List<MenuMenuItemDto> listaAccesos(String perfil, Properties idioma) {
        List<MenuMenuItemDto> lista = new ArrayList<>();
        lista.add(new MenuMenuItemDto("miprincipal", ViewNavigator.ViewId.LOGIN.getFxmlPath(),
                idioma.getProperty("menu.nombre.principal"), idioma.getProperty("menuitem.nombre.salir"),
                "Salir", "S"));
        lista.add(new MenuMenuItemDto("miproducto", ViewNavigator.ViewId.MAIN_PRODUCT.getFxmlPath(),
                "Producto", "Adm. Producto",
                "Gestión Productos", "T"));
        lista.add(new MenuMenuItemDto("micliente", ViewNavigator.ViewId.MAIN_CLIENT.getFxmlPath(),
                "Venta", "Reg. Cliente",
                "Gestionar Cliente", "T"));
        lista.add(new MenuMenuItemDto("miventa", ViewNavigator.ViewId.MAIN_SALE.getFxmlPath(),
                "Venta", "Reg. Venta",
                "Gestionar Ventas", "T"));

        List<MenuMenuItemDto> accesoReal = new ArrayList<>();

        accesoReal.add(lista.get(0));
        switch (perfil) {
            case "Administrador":
                accesoReal.add(lista.get(2));
                accesoReal.add(lista.get(3));
                break;
            case "Root":
                accesoReal = lista;
                break;
            case "Reporte":
                accesoReal.add(lista.get(1));
                accesoReal.add(lista.get(2));
                break;
            default:
                throw new AssertionError();
        }
        return accesoReal;
    }

    @Override
    public Map<String, String[]> accesosAutorizados(List<MenuMenuItemDto> accesos) {
        Map<String, String[]> menuConfig = new HashMap<>();
        for (MenuMenuItemDto menu : accesos) {
            menuConfig.put(menu.getIdNombreObj(), new String[]{menu.getRutaFile(),
                    menu.getNombreTab(),menu.getTipoTab()});
        }
        return menuConfig;
    }
}

