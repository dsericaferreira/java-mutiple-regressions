/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package múltiplasregressões;

import Math.Record;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Érica Carneiro
 */
public class Predict {
    
    //Recebe uma lista com os dados que gerarão a previsão.
    //Time, week, year...
    List<Record> prever = new ArrayList<>();
    double[][] matrix;
    double [][] coef1;
    double [][] coef2;
    double [][] coef3;
    
    
    public Predict(double[][] coef1, double[][] coef2, double[][] coef3) {
        this.coef1=coef1;
        this.coef2 = coef2;
        this.coef3 = coef3;
    }
    
    
    
    public void setMatrix(List<Record> lista){
        this.matrix = new double[lista.size()][lista.get(0).getNumAttributes()+3];
        //Carrega a matrix onde cada linha é um record
        //Cada coluna é uma feature.
        //Cada linha será uma previsão.
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length-3; j++) {
                matrix[i][j] = lista.get(i).getFeature(j);
            }
        }
       
        
    }
    
    public double previsão(double[][] coef, double[] values) {
        
        //Método de preve os resultados e adiciona os mesmos na matriz.
        double previsao =0;
        
        for(int i=0; i<values.length; i++) {
            previsao = (coef[i+1][0] * values[i]) + previsao;
        }
         previsao = previsao + coef[0][1];
        
        return previsao;
        
    }
    
}
