/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;
import Math.Regression;
import java.util.ArrayList;
import java.util.List;
import java.math.*;

/**
 * Análise de regressão.
 * Calculamos todas as regressões e calculamos o erro acumulado
 * fazendo o somatório de todas as diferenças encontradas entre os valores 
 * reais e os valores calculados. 
 * 
 * @author Érica Carneiro
 */
public class AnaliseRegressao {
    
    
    //Se quero analisar determinada regressao, passo como lista
    // os valores reais do y em questão.
    double[][] predicted;
    double[][] dados, coeficientes, variaveis;

    public double[][] getPredicted() {
        return predicted;
    }

    public void setPredicted(double[][] predicted) {
        this.predicted = predicted;
    }

    public double[][] getDados() {
        return dados;
    }

    public void setDados(double[][] dados) {
        this.dados = dados;
    }

    public double[][] getCoeficientes() {
        return coeficientes;
    }

    public void setCoeficientes(double[][] coeficientes) {
        this.coeficientes = coeficientes;
    }

    public double[][] getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(double[][] variaveis) {
        this.variaveis = variaveis;
    }
    
     
    
    public AnaliseRegressao(double [][] coef, double[][] dados, double[][] variaveis) {
        this.dados = dados;
        coeficientes = coef;
        this.variaveis = variaveis; //Valores reais de x1 e x2.
    }

    
    public void aplicaRegressao(double [][] entrada, double[][] coeficients) {
        predicted = new double[entrada.length][1];
        int numColumns = entrada[0].length;
        
        for (int i=0; i<entrada.length; i++) {
            for (int j=0; j<coeficients.length-1; j++) {
            predicted[i][0]= predicted[i][j]+(coeficients[j+1][0]*entrada[i][j]);
            }
            predicted[i][0] = predicted[i][0] + coeficients[0][0];
        }
        
        //Com esse método temos os valores calculados da variável pergunta.
        
    }
    
    public double calculaErroAcumulado(double[][] vars, double[][] predicted) {
        double sse = 0;
        for(int i=0; i<predicted.length; i++) {
            sse = Math.pow((vars[i][0]- predicted[i][0]), 2) + sse;
        }
        
        return sse;
        
    }
  
    public double calculaYAcumulado(double[][] vars) {
        
        double ssy =0;
        
        for(int i=0; i<vars.length; i++) {
            ssy = Math.pow(vars[i][0], 2) + ssy;
        }
        
        return ssy;
    }
    
    public double calculaMediaAcumulada(double[][] vars) {
        
        double ss0 = 0;
        
        for(int i=0; i<vars.length; i++) {
            
            ss0 = vars[i][0] + ss0;
            
        }
        
        ss0 = Math.pow(ss0/(vars.length),2)*(vars.length);
        
        
        return ss0;
    }
    
    public double calculaIndiceExplicação(){
        double squaredR =0;
        double sst = this.calculaYAcumulado(this.getVariaveis()) - 
                this.calculaMediaAcumulada(this.getVariaveis());
        double ssr = sst - this.calculaErroAcumulado(this.getVariaveis(), this.getPredicted());
        
        squaredR = ssr/sst;
        
        return squaredR;
    }
    
    
    
}
