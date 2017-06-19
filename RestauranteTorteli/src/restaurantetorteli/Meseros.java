/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantetorteli;

public class Meseros
{
    private String mes;
    private String mesero;
    private int venta;
   
    public Meseros()
    {
    }

    public Meseros(String mes, String mesero, int venta) {
        this.mes = mes;
        this.mesero = mesero;
        this.venta = venta;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMesero() {
        return mesero;
    }

    public void setMesero(String mesero) {
        this.mesero = mesero;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }
    
   
}