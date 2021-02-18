/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;

/**
 *
 * @author Samy
 */
public class Persona {
    
    private int Id;
    private String nombre;
    private String correo;
    private int tel;

    //Constructores 
    public Persona() {
    }

    public Persona(int Id, String nombre, String correo, int tel) {
        this.Id = Id;
        this.nombre = nombre;
        this.correo = correo;
        this.tel = tel;
    }
    
    //Metodos set

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    //Metodos get

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getTel() {
        return tel;
    }
    
    //Metodo toString

    @Override
    public String toString() {
        return "Persona{" + "Id=" + Id + ", nombre=" + nombre + ", correo=" + correo + ", tel=" + tel + '}';
    }
    
}
