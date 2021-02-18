/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Persona;
import Modelo.PersonaSQL;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author Samy
 */
public class Control implements ActionListener, MouseListener {

    Persona persona;
    PersonaSQL perSQL = new PersonaSQL();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Control(Vista vista) {
        this.vista = vista;
        this.vista.bnAgregar.addActionListener(this);
        this.vista.bnModificar.addActionListener(this);
        this.vista.bnEliminar.addActionListener(this);
        this.vista.table.addMouseListener(this);
        this.modelo = (DefaultTableModel) vista.table.getModel();
        listar();
    }

    public void actualizarTabla() {
        limpiarTablas();
        listar();
    }

    public void listar() {
        ArrayList<Persona> listaP = perSQL.listar();
        listaP.forEach((p) -> {
            modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getCorreo(), p.getTel()});
        });
        listaP.clear();
    }

    public void limpiarTablas() {
        while (modelo.getRowCount() != 0) {
            modelo.removeRow(0);
        }
    }

    public void limpiarField() {
        vista.fieldID.setText("");
        vista.fieldNombre.setText("");
        vista.fieldNac.setText("");
        vista.fieldTel.setText("");
    }

    @Override  //Captura los eventos de los botones
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.bnAgregar) {
            persona = new Persona(0, vista.fieldNombre.getText(), vista.fieldNac.getText(), Integer.parseInt(vista.fieldTel.getText()));
            perSQL.agregar(persona);
            actualizarTabla();
            JOptionPane.showMessageDialog(vista, "Agregado");
            limpiarField();
        }

        if (e.getSource() == vista.bnModificar) {
            if (!vista.fieldID.getText().equals("")) {
                if (0 == JOptionPane.showConfirmDialog(vista, "Seguro quieres modificar al usuario con le identificador  " + vista.fieldID.getText())) {
                    persona = new Persona(Integer.parseInt(vista.fieldID.getText()), vista.fieldNombre.getText(), vista.fieldNac.getText(), Integer.parseInt(vista.fieldTel.getText()));
                    perSQL.actualizar(persona);
                    actualizarTabla();
                    JOptionPane.showMessageDialog(vista, "Usuario modificado");
                    limpiarField();
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una persona.");
            }
        }

        if (e.getSource() == vista.bnEliminar) {
            if (!vista.fieldID.getText().equals("")) {
                if (0 == JOptionPane.showConfirmDialog(vista, "Seguro quieres eliminar al usuario con le identificador  " + vista.fieldID.getText())) {
                    perSQL.eliminar(Integer.parseInt(vista.fieldID.getText()));
                    actualizarTabla();
                    JOptionPane.showMessageDialog(vista, "Usuario eliminado.");
                    limpiarField();
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una persona");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.table) {
            int fila = vista.table.getSelectedRow();
            vista.fieldID.setText((int) modelo.getValueAt(fila, 0) + "");
            vista.fieldNombre.setText((String) modelo.getValueAt(fila, 1));
            vista.fieldNac.setText((String) modelo.getValueAt(fila, 2));
            vista.fieldTel.setText((int) modelo.getValueAt(fila, 3) + "");
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        //To change body of generated methods, choose Tools | Templates.
    }
}
