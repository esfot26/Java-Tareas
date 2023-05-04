/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.materias;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class usuario {
    int id;
    String codigo;
    String nombre;
    String docente;
    String inicio;
    String fin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

   
    public void InsertarMateria(JTextField paramid, JTextField paramcodigo, JTextField paramnombre, JTextField paramdocente, JTextField paraminicio){
    setId(Integer.parseInt(paramid.getText()));
    setCodigo(paramcodigo.getText());
    setNombre(paramnombre.getText());
    setDocente(paramdocente.getText());  
    setInicio(paraminicio.getText());
    setFin(paramfin.getText());
    conexion objetoConexion = new conexion();
    String consulta="Insert into usuario(codigo,nombre,docente,inicio,fin) VALUES(?,?,?,?,?);";
    try{
       CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
       cs.setString(1, getCodigo());
       cs.setString(2, getNombre());
       cs.setString(3, getDocente());
       cs.setString(4, getInicio());
       cs.setString(5,getFin());
       cs.execute();
       JOptionPane.showMessageDialog(null,"Se creo correctamente!");
       
    }catch(Exception e){
               JOptionPane.showMessageDialog(null,"No se creo correctamente!"+e.toString());
        }
    }
    public void MostrarTabla2(JTable paramtabla2){
        conexion ObjetoConexion=new conexion();
        DefaultTableModel model= new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla= new TableRowSorter<TableModel> (model);
        paramtabla2.setRowSorter(OrdenarTabla);
        String sql="";
        model.addColumn("Id");
        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Docente");
        model.addColumn("Inicio");
        model.addColumn("Fin");
        sql="SELECT * FROM usuario";
        String [] datos= new String[6];
        Statement st;
        try{
            st=ObjetoConexion.establecerConexion().createStatement();
            ResultSet resul= st.executeQuery(sql);
            while(resul.next()){
                datos[0]=resul.getString(1);
                datos[1]=resul.getString(2);
                datos[2]=resul.getString(3);
                datos[3]=resul.getString(4);
                datos[4]=resul.getString(5);
                datos[5]=resul.getString(6);
                model.addRow(datos);
            }
            paramtabla2.setModel(model);
    }catch(SQLException e){
               JOptionPane.showMessageDialog(null,"Error al mostrar los registros!"+e.toString());
        }
    }
    public void seleccionar(JTable paramtabla2,JTextField paramcodigo,JTextField paramnombre,JTextField paramdocente,JTextField paraminicio,JTextField paramfin){
     
        try{
            int fila= paramtabla2.getSelectedRow();
            if(fila>=0){
                paramcodigo.setText((String) paramtabla2.getValueAt(fila,1));
                paramnombre.setText((String) paramtabla2.getValueAt(fila,2));
                paramdocente.setText((String) paramtabla2.getValueAt(fila,3));
                paraminicio.setText((String) paramtabla2.getValueAt(fila,4));
                paramfin.setText((String) paramtabla2.getValueAt(fila,5));
            }
            else{
               JOptionPane.showMessageDialog(null,"Error al mostrar los registros!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al seleccionar los registros!"+e.toString());
        }
        
    }
    public void modificar(JTextField paramcodigo,JTextField paramnombre,JTextField paramdocente,JTextField paraminicio,JTextField paramfin){
        setCodigo(paramcodigo.getText());
        setNombre(paramnombre.getText());
        setDocente(paramdocente.getText());
        setInicio(paraminicio.getText());
        setFin(paramfin.getText());
        
        conexion objetoConexion= new conexion();
        String consulta="UPDATE `usuario` SET `id`='?',`codigo`='?',`nombre`='?',`docente`='?',`inicio`='?',`fin`='?' WHERE 'usuario' ";
        
        try{
            CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getCodigo());
            cs.setString(2, getNombre());
            cs.setString(3, getDocente());
            cs.setString(4, getInicio());
            cs.setString(5,getFin());
            cs.execute();
       JOptionPane.showMessageDialog(null,"Se creo correctamente!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No Se creo correctamente!"+e.toString());
        }
        
    }
    public void eliminar(JTextField paramid){
        setId(Integer.parseInt(paramid.getText()));
        conexion objetoConexion= new conexion();
         
        
        String consulta="DELETE FROM usuario WHERE 0";
        try{
            CallableStatement cs= objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setInt(0, getId());
            cs.execute();
            
       JOptionPane.showMessageDialog(null,"Se elimino correctamente!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No Se elimino correctamente!"+e.toString());
        }
     }
}

