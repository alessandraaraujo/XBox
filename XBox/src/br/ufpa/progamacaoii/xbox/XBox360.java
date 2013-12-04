package br.ufpa.progamacaoii.xbox;

import static br.ufpa.progamacaoii.xbox.XBox.maxControles;
import br.ufpa.progamacaoii.xbox.data.Data;
import java.util.List;
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
    
    public XBox360(Data data,XBox360 xbox){
        super( data,(XBox) xbox );//usando cast
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
                    usarKinect();
                }
                
                rodaMidiaFisica();
            }
            else
                JOptionPane.showMessageDialog(null, "\nQuantia de jogadores invalida\n","Limite excedido",JOptionPane.ERROR_MESSAGE);
        }
        else
            rodaMidiaVirtual();
    }
    
    public double getMemRam() {
        return memRam;
    }

    public void setMemRam(double memRam) {
        this.memRam = memRam;
    }

    public double getDiscoLocal() {
        return discoLocal;
    }

    public void setDiscoLocal(double discoLocal) {
        this.discoLocal = discoLocal;
    }

    public double getGpu() {
        return gpu;
    }

    public void setGpu(double gpu) {
        this.gpu = gpu;
    }

    public int getQtNucleos() {
        return qtNucleos;
    }

    public void setQtNucleos(int qtNucleos) {
        this.qtNucleos = qtNucleos;
    }

    public int getQtControles() {
        return qtControles;
    }

    public void setQtControles(int qtControles) {
        this.qtControles = qtControles;
    }

    public Kinect getKinect() {
        return kinect;
    }

    public void setKinect(Kinect kinect) {
        this.kinect = kinect;
    }

    public String getJogoRodando() {
        return jogoRodando;
    }

    public void setJogoRodando(String jogoRodando) {
        this.jogoRodando = jogoRodando;
    }

    public List<String> getJogos() {
        return jogos;
    }

    public void setJogos(List<String> jogos) {
        this.jogos = jogos;
    }

    public Data getFabricacao() {
        return fabricacao;
    }

}
