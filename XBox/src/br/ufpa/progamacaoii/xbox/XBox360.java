package br.ufpa.progamacaoii.xbox;

import static br.ufpa.progamacaoii.xbox.XBox.maxControles;
import javax.swing.JOptionPane;

/**
 *
 * @author Moisés Felipe
 */
public class XBox360 extends XBox{
    
    /*
     * construtor vazio
     */
    public XBox360(){
        super();
    }
    /*
     * construtor de copia
     */
    public XBox360(XBox360 xbox){
        super( (XBox) xbox );//usando cast
    }
    
    @Override
    public void rodarJogo(){
        
        if (JOptionPane.showConfirmDialog(null, "\nUsar midia física?\n","Iniciando...",JOptionPane.YES_NO_OPTION) == 0 ){
            this.jogoRodando = JOptionPane.showInputDialog(null,"\nInforme qual o jogo a executar\n","Iniciando...",JOptionPane.INFORMATION_MESSAGE);
            int aux = Integer.parseInt(JOptionPane.showInputDialog(null,"\nInforme a quantia de jogadores\n","Iniciando...",JOptionPane.INFORMATION_MESSAGE));
            
            if(aux > 0 && aux <= maxControles){
                qtControles = aux;
                
                if (JOptionPane.showConfirmDialog(null, "\nUsar kinect?\n","Iniciando...",JOptionPane.YES_NO_OPTION) == 0 ){
                    if(kinect == null)
                        kinect = new Kinect();
                    kinect.usar(this);
                }
                
                rodaMidiaFisica();
            }
            else
                JOptionPane.showMessageDialog(null, "\nQuantia de jogadores invalida\n","Limite excedido",JOptionPane.ERROR_MESSAGE);
        }
        else
            rodaMidiaVirtual();
    }
    
    
    
}
