package Math;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author A Paz
 * @author Rex
 */

//Esta classe representa um registro (record)
public class Record {
    //Atributos
    private String description; //Alguma breve descrição sobre o registro
    private double[] features;  //Vetor valores de seus atributos


    //Construtores da classe
    public Record(String description, double[] features) {
        this.description = description;
        this.features = features;
    }
    public Record(double[] features) {
        this.description = "";
        this.features = features;
    }
    public Record(){}
    
    
    //Métodos úteis
    //Este metodo realiza o print no console dos valores dos atributos deste registro.
    public void print(){
        System.out.println(this.description);
        for (int i=0; i<this.features.length; i++){
            double value = this.features[i];
            System.out.println("Attribute " + i + ": " + this.features[i]);
        }
    }
    
    
    //Este metodo retorna o valor da feature i
    public double getFeature(int i){
        return this.features[i];
    }
    
    
    //Este metodo retorna o numero de atributos deste registro.
    public int getNumAttributes(){
        return this.features.length;
    }
    
    
    //Este metodo cria uma copia exata do registro dado como parametro
     public static Record copy(Record record){
        Record copy = new Record();
        
        copy.description = record.getDescription();
        copy.features = new double[record.getFeatures().length];
        for(int i=0; i<copy.features.length; i++){
            copy.features[i] = record.getFeatures()[i];
        }
        
        return copy;
    }
    
    
    //HashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Arrays.hashCode(this.features);
        return hash;
    }

    //Equals
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Record other = (Record) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Arrays.equals(this.features, other.features)) {
            return false;
        }
        return true;
    }
    
    
    //Gets e Sets
    public double[] getFeatures() {
        return features;
    }

    public void setFeatures(double[] features) {
        this.features = features;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
