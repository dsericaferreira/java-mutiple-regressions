/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package múltiplasregressões;
import Math.Record;
import Math.Regression;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Érica Carneiro
 */
public class DataIn {
    
    //O totalData lida com uma porcentagem do conjuntode dados inseridos.
    //A lógica de porcentagens será avaliada e inserida em outra classe.
    List<Record> totalData;
    double[][] centerx, coeficients1;
    double[][] centery, coeficients2;
    double[][] radius, coeficients3;
    double [][] data;
    //Lidaremos com 3 regressões. 
    public DataIn(List<Record> lista) {
        totalData = lista;
        data = new double[lista.size()][lista.get(0).getNumAttributes()];
        this.data = setDataMatrix(this.totalData);
        
       
    }

    
    
    
    //Filtering Data into different records.
    
    public double[][] setDataMatrix(List<Record> initialSet) {
        
        double[][] matrix = new double[initialSet.size()][initialSet.get(0).getNumAttributes()];
        
        for(int i=0; i<initialSet.size(); i++) {
            for(int j=0; i<initialSet.get(0).getNumAttributes(); j++) {
                //Matrix generated!!!
                matrix[i][j]= initialSet.get(i).getFeature(j);
                
            }
            
        }
        
        return matrix;
    }
    
    public void setData(double[][] matrix) {
        this.data = setDataMatrix(totalData);
    }
    //Lógica de regressão;
    //Como terei 3 regressões (DENGUE) terei 4 matrizes.
    
    public double[][] getData() {
        return this.data;
    }
    
    
    public double[][] chargeMatrixCenter(double [][] data, int regression) {
    //Matriz que recebe os dados das 2 primeiras regressões. 
    //Regressões que preveem os centros (X and Y).
    int numAttributes = data[0].length;
    numAttributes = numAttributes -2;
    double[][] matrix = new double[data.length][numAttributes];
    //Regression is an index of control. 
    for (int i=0; i<data.length; i++) {
        for(int j=0; j<matrix[0].length-1; j++) {
            matrix[i][j] = data[i][j];
        }
        
    }
    
    for(int i=0; i<matrix.length; i++) {
        matrix[i][numAttributes] =  data[i][regression];
    }
    return matrix;
    }
    
    /* Método a ser considerado caso o Raio seja calculado por XC e YC
    public double[][] chargeMatrixRadius(double[][] data) {
        
        double matrix[][] = new double[data.length][3];
        int aux = (data[0].length) -3;
        for(int i=0; i< matrix.length; i++) {
            for(int j=0; j<3; j++) {
                
                matrix[i][j]= data[i][aux+j];
            }
        }
        
        return matrix;
    }
    */
    public void calculate() {
    
        // Carregar matrix Centro x
        Regression regressao;
        int numcolunas = this.getData().length;
        
        this.centerx = this.chargeMatrixCenter(this.getData(), (numcolunas-3));
        regressao = new Regression();
        this.coeficients1 = regressao.getRegression(this.centerx);
       
        this.centery = this.chargeMatrixCenter(this.getData(), numcolunas-2);
        regressao = new Regression();
        this.coeficients2 = regressao.getRegression(this.centery);
        
        this.radius = this.chargeMatrixCenter(this.getData(), numcolunas-1);
        regressao = new Regression();
        this.coeficients3 = regressao.getRegression(this.radius);
        
        
    
    }
    
    public double[][] getCoefficient1() {
     return this.coeficients1;   
    }
    
    public double[][] getCoefficient2() {
     return this.coeficients2;   
    }
    public double[][] getCoefficient3() {
     return this.coeficients3;   
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
