
package br.ufpa.progamacaoii.xbox;

import javax.swing.JOptionPane;

/**
 *
 * @author Moisés Felipe
 */
public class Kinect {
    
    /*
     * indica se o kinect esta ativo
     */
    private boolean ativo;
    /*
     * coordenadas do objeto capturado pelo kinect
     */
    private Double x;
    private Double y;
    private Double z;
    
    /*
     * construtor chamado por default (vazio)
     */
    public Kinect(){
        ativo = false;
        x = null;
        y = null;
        z = null;
    }
    /*
     * construtor de copia
     */
    public Kinect(Kinect kinect){
        ativo = kinect.ativo;
        x = kinect.x;
        y = kinect.y;
        z = kinect.z;
    }
    /*
     * modo de utilizar o kinect através do equipamento
     */
    public void usar(Device equipamento){
        ativo = true;
        
        if(z != null && x != null && y != null){
            ativo = true;
            JOptionPane.showMessageDialog(null, "\nKinect ativo\n\nObjeto detectado em\n\nX -> "+x
                +"\nY -> "+y+"\nZ -> "+z+"\n","Kinect: "+equipamento.nome,JOptionPane.DEFAULT_OPTION);        
        }
        else
            JOptionPane.showMessageDialog(null, "\nKinect ativo\n\nNenhum objeto detectado\n","",JOptionPane.DEFAULT_OPTION);            
        
    }
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }
}
