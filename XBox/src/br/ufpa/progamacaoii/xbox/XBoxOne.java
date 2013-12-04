package br.ufpa.progamacaoii.xbox;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Moisés Felipe
 */
public class XBoxOne extends XBox{

    /*
     * lista de canais utilizaveis no XBox One.
     */
    private List<String> canais;
            
    /*
     * indica se o usuario esta online
     */
    private boolean online;
            
    public XBoxOne(){
        super();//construtor da classe pai
        //valores default        
        this.canais = new ArrayList<>();        
    }
    
    public XBoxOne(XBoxOne xbox){
        super( /*(XBox)*/ xbox);//um XBox One eh um XBox (cast nao eh obrigatorio, mas se quiser usar tbm funciona :) )
        this.canais = xbox.canais;        
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
    
    public void tv(){
        
        /*
         * verificacao de seguranca.
         * se nao houver nenhuma canal na memoria secundaria.
         */
        if(canais.size() == 0)
            if(JOptionPane.showConfirmDialog(null, "\nSem canais registrados.\n\nAdicionar?","TV",JOptionPane.YES_NO_OPTION) == 0 )
                if(!addCanal())
                    return;
        
        String texto = "";
        
        for(int i = 0; i<canais.size(); i++)
            texto += (i+1)+". "+canais.get(i) +'\n';//concatena os canais armazenados localmente
        
        int canal = Integer.parseInt( JOptionPane.showInputDialog(null, "\nSelecione um canal:\n\n"+texto+"\n","TV",JOptionPane.INFORMATION_MESSAGE) );
        
        if(canal <= canais.size() && canal > 0)
            JOptionPane.showMessageDialog(null, "\nAssistindo no momento a TV "+canais.get(canal-1)+"\n","TV",JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "\nOpção inválida\n","Erro",JOptionPane.ERROR_MESSAGE);
    }
    
    public boolean addCanal(){
        
        String canal = JOptionPane.showInputDialog(null, "\nInforme qual o canal a ser armazenado\n\nExemplo: SBT - 5","Adicionar Canal",JOptionPane.INFORMATION_MESSAGE);
        
        if(!canal.equals("")){
            if(canal.split("-").length == 2) {//verifica se o canal informado esta no formato <nome canal> - <numero da estacao>
                canais.add(canal);
                return true;
            }
        }
        
        JOptionPane.showMessageDialog(null, "\nCanal Inválido\n","Adicionar Canal",JOptionPane.ERROR_MESSAGE);
        return false;        
    }
    /*
     * apenas se conecta a central da XBox Live e inicia um jogo 
     */
    public void jogarOnline(){
        try {
            if(conectaCentral())
                rodarJogo();            
            else
                JOptionPane.showMessageDialog(null, "\nImpossível se conectar a XBox Live no momento\nTente novamente mais tarde.\n",
                        "Erro na conexão",JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            Logger.getLogger(XBoxOne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
