import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTextField txtID;
    private JTextField txtNombre;
    private JComboBox cboPosicion;
    private JSpinner spiRendimiento;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JList lstEquipo;
    private JButton btnMostrar;
    int codigo= 0;
    Equipo equipo1= new Equipo();

    public Ventana() {
        SpinnerNumberModel snm = new SpinnerNumberModel(10,1,20,1);
        spiRendimiento.setModel(snm);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= Integer.parseInt(txtID.getText());
                String nombre= txtNombre.getText();
                String posicion= cboPosicion.getSelectedItem().toString();
                int rendimiento= Integer.parseInt(spiRendimiento.getValue().toString());
                if(id<=codigo){
                    JOptionPane.showMessageDialog(null, "ID no válido");
                }else{
                    Jugador j= new Jugador(id, nombre, posicion, rendimiento);
                    equipo1.agregar(j);
                    JOptionPane.showMessageDialog(null, "Jugador agregado");
                    codigo= id;
                }
            }
        });
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel dlm = new DefaultListModel();
                for(Jugador j:equipo1.todos()){
                    dlm.addElement(j.toString());
                }
                lstEquipo.setModel(dlm);
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= Integer.parseInt(txtID.getText());
                String nombre= txtNombre.getText();
                String posicion= cboPosicion.getSelectedItem().toString();
                int rendimiento= Integer.parseInt(spiRendimiento.getValue().toString());
                Jugador editar= new Jugador(id, nombre, posicion, rendimiento);
                if(equipo1.editar(id, editar)){
                    JOptionPane.showMessageDialog(null,"Editado correctamente, muestre los datos");
                }else{
                    JOptionPane.showMessageDialog(null, "No existe el ID");
                }
            }
        });
        lstEquipo.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstEquipo.getSelectedIndex()!=-1){
                    int indice= lstEquipo.getSelectedIndex();
                    Jugador seleccionado= equipo1.todos().get(indice);
                    txtID.setText(""+seleccionado.getId());
                    txtNombre.setText(seleccionado.getNombre());
                    cboPosicion.setSelectedItem(seleccionado.getPosicion());
                    spiRendimiento.setValue(seleccionado.getRendimiento());
                    JOptionPane.showMessageDialog(null, "Revise los datos en el ingreso");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

