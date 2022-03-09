package Math;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;


/* To change this license header, choose License Headers in Project Properties. 


* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Érica Carneiro
 */

public class Regression {
    
    
    
    
    double[][] data, coeficients;
    
    
    public Regression( ) {
    
 
    
    }
    
    public void setData(double[][] matrix) {
        data = matrix;
    }
    
    public double[][] getData() {
        return this.data;
    }
    
    
    
    //Método que irá gerar a matriz principal com os atributos a serem 
    //considerados como variáveis explicativas.
     private double[][] getMatrixEplicativas(double[][] matrix){
        //Variaveis auxiliares
        int numAttributes = (matrix[0].length);
        double[][] explicativas = new double[matrix.length][numAttributes]; 
        //Matriz: registroXatributo
        //Montar a matriz do dataset (conjunto de dados)
        for(int i=0; i<explicativas.length; i++){
            //Primeira coluna da matriz é preenchida em 1's
            explicativas[i][1]=1;
            for(int j=0; j<numAttributes; j++){
                explicativas[i][j+1] = matrix[i][j];
            }
        }
        
        return explicativas;
    }
    
    private double[][] getMatrixRespostas(double[][] matrix){
        //Variaveis auxiliares
        double[][] respostas = new double[matrix.length][1]; 
        //Matriz: registroXatributo
        //Montar a matriz com variáveis respostas (z)
        int column = (matrix[0].length);
        column = column -1;
        for(int i=0; i<matrix.length; i++){
            respostas[i][1] = matrix [i][column];
        }
        
        return respostas;
    }
    
    private RealMatrix getCovarianceMatrix(double [][] matrix){
        
        Covariance matrixCov;
        
        try{
            matrixCov = new Covariance(matrix);
        } catch (MathIllegalArgumentException e){
            System.out.println("Ocorreu um erro inesperado");
            return null;
        }
        return matrixCov.getCovarianceMatrix();
        
    }
    
    private RealMatrix calculate () {
        
        double [][] explicativas = this.getMatrixEplicativas(this.getData());
        double [][] respostas = this.getMatrixRespostas(this.getData());
        
        RealMatrix covariance = this.getCovarianceMatrix(explicativas);
        RealMatrix resp = new Array2DRowRealMatrix(respostas);
        RealMatrix explic = new Array2DRowRealMatrix(explicativas);
         //Calculo do inverso da covariancia por decomposicao LU
        DecompositionSolver solver = new LUDecomposition(covariance).getSolver();
        //if (solver.isNonSingular() == false){return -1;}    
        //Caso tenhamos uma matriz singular, nao retornar valor de calculo...
        RealMatrix matrixCovInverted = solver.getInverse();
        
        RealMatrix explicaTrans = explic.transpose(); //Transposta do vetor diferenca
    
        RealMatrix produto = explicaTrans.multiply(resp);
        produto = matrixCovInverted.multiply(produto);
        this.setCoeficientes(produto);
        return produto;
    }
    
    public void setCoeficientes(RealMatrix x) {
        
        this.coeficients = x.getData();      
       
    
    }
    
    public double[][] getCoeficientes() {
        return this.coeficients;
    }
    
    public void printCoeficientes() {
     
        for(int i=0; i< coeficients.length; i++) {
            
            System.out.println("Coeficiente " + (i+1) + ": " + coeficients[i][1]);
        }
        
    }
    
    //Método calcula-se regressão 1.
    
    public double[][] getRegression(double [][] matrix) {
        this.setData(matrix);
        double [][] coeficients;
        coeficients = this.calculate().getData();
        
        return coeficients;
    }
     
    
}
