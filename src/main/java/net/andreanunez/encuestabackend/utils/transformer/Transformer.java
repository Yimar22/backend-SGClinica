package net.andreanunez.encuestabackend.utils.transformer;

//esta interfaz se encarga de transformar de un objeto hacia otro objeto
public interface Transformer<K, T> {
    T transformData(K data);
}
