package pe.edu.upeu.asistencia.control;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.enums.Carrera;
import pe.edu.upeu.asistencia.enums.TipoParticipante;
import pe.edu.upeu.asistencia.modelo.Participante;
import pe.edu.upeu.asistencia.servicio.ParticipanteServicioI;

@Controller
public class ParticipanteController {
    @FXML
    private ComboBox<Carrera> cbxCarrera;

    @FXML
    private ComboBox<TipoParticipante> cbxTipoParticipante;

    @FXML
    private TextField txtDni, txtNombres, txtApellidos;

    @FXML
    private TableView<Participante> tableRegPart;
    ObservableList<Participante> participantes;

    @Autowired
    ParticipanteServicioI ps;
    TableColumn<Participante, String> dniCol, nombreCol, apellidosCol, carreraCol, tipoParticipanteCol;
    TableColumn<Participante, Void> opcCol;

    int indexEdit = -1;

    @FXML
    public void initialize(){
        cbxCarrera.getItems().addAll(Carrera.values());
        cbxTipoParticipante.getItems().addAll(TipoParticipante.values());
        // ? cbxCarrera.getSelectionModel().select(1);
        // ? Carrera carrera = cbxCarrera.getSelectionModel().getSelectedItem();
        // ? System.out.println(carrera.name());
        definirColumnas();
        listarParticipantes();
    }

    public void limpiarFormularios(){
        txtDni.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        cbxCarrera.getSelectionModel().clearSelection();
        cbxTipoParticipante.getSelectionModel().clearSelection();
    }

    @FXML
    public void registrarParticipante(){
        Participante p = new Participante();
        p.setDni(txtDni.getText());
        p.setNombre(txtNombres.getText());
        p.setApellidos(txtApellidos.getText());
        p.setCarrera(cbxCarrera.getSelectionModel().getSelectedItem());
        p.setTipoParticipante(cbxTipoParticipante.getSelectionModel().getSelectedItem());
        p.setEstado(true);
        if (indexEdit == -1) {
            ps.save(p);
        }
        else {
            ps.update(p);
            indexEdit = -1;
        }
        limpiarFormularios();
        listarParticipantes();
    }

    public void definirColumnas(){
        dniCol = new TableColumn<>("DNI");
        nombreCol = new TableColumn<>("Nombre");
        apellidosCol = new TableColumn<>("Apellidos");
        carreraCol = new TableColumn<>("Carrera");
        tipoParticipanteCol = new TableColumn<>("Tipo Participante");
        opcCol = new TableColumn<>("Opciones");
        opcCol.setMinWidth(200);
        tableRegPart.getColumns().addAll(dniCol,nombreCol, apellidosCol, carreraCol, tipoParticipanteCol, opcCol);
    }

    public void listarParticipantes(){
        dniCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDni()));
        nombreCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        apellidosCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
        carreraCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarrera().toString()));
        tipoParticipanteCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoParticipante().name()));
        agregarAccionesButton();
        participantes = FXCollections.observableList(ps.findAll());
        tableRegPart.setItems(participantes);
    }

    public void eliminarParticipante(String dni){
        ps.delete(dni);
        listarParticipantes();
    }

    public void editarParticipante(Participante p, int index) {
        txtDni.setText(p.getDni());
        txtNombres.setText(p.getNombre());
        txtApellidos.setText(p.getApellidos());
        cbxCarrera.getSelectionModel().select(p.getCarrera());
        cbxTipoParticipante.getSelectionModel().select(p.getTipoParticipante());
        indexEdit = index;
    }

    public void agregarAccionesButton(){
        Callback<TableColumn<Participante, Void>, TableCell<Participante, Void>>
                cellFactory = param -> new TableCell<>(){
            private final Button btnEdit = new Button("Editar");
            private final Button btnDelete = new Button("Eliminar");
            {
                btnEdit.setOnAction(event -> {
                    Participante p = getTableView().getItems().get(getIndex());
                    editarParticipante(p, getIndex());
                });
                btnDelete.setOnAction(event -> {
                    Participante p = getTableView().getItems().get(getIndex());
                    eliminarParticipante(p.getDni());
                });
            }
            @Override
            public void updateItem(Void item, boolean empty){
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                }
                else {
                    HBox hbox = new HBox(btnEdit, btnDelete);
                    hbox.setSpacing(10);
                    setGraphic(hbox);
                }
            }
        };
        opcCol.setCellFactory(cellFactory);
    }
}
