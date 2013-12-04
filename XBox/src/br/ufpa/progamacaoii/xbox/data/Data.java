
package br.ufpa.progamacaoii.xbox.data;

/**
 *
 * @author Felipe
 */
public class Data {
    
    protected int dia;
    protected int mes;
    protected int ano;
    
    public Data(){
        ano = 2010;
        this.dia = 1;
        this.mes = 1;    
    }
    
    public Data(final int dia, final int mes, final int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Data(final Data data){
        this.dia = data.dia;
        this.mes = data.mes;
        this.ano = data.ano;
    }
    
    public int getDia() {
        return dia;
    }

    public void setDia(final int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(final int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(final int ano) {
        this.ano = ano;
    }
    
}
