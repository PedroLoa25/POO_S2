package pe.edu.upeu.sysventas.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.upeu.sysventas.dto.MenuMenuItemDto;
import pe.edu.upeu.sysventas.service.IMenuMenuItemDao;

import java.util.*;

@Service
public class MenuMenuItemDaoImp implements IMenuMenuItemDao {
    @Override
    public List<MenuMenuItemDto> listaAccesos(String perfil, Properties idioma) {
        List<MenuMenuItemDto> lista = new ArrayList<>();
        lista.add(new MenuMenuItemDto("miprincipal", "/view/login.fxml",
                idioma.getProperty("menu.nombre.principal"), idioma.getProperty("menuitem.nombre.salir"),
                "Salir", "S"));
        lista.add(new MenuMenuItemDto("miproducto", "/view/main_producto.fxml",
                "Producto", "Adm. Producto",
                "Gestión Productos", "T"));
        lista.add(new MenuMenuItemDto("micliente", "/view/main_cliente.fxml",
                "Venta", "Reg. Cliente",
                "Gestionar Cliente", "T"));
        lista.add(new MenuMenuItemDto("miventa", "/view/main_venta.fxml",
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
            menuConfig.put("mi"+menu.getIdNombreObj(), new String[]{menu.getRutaFile(),
                    menu.getNombreTab(),menu.getTipoTab()});
        }
        return menuConfig;
    }
}

