/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vgb.model;

import vgb.domain.VGBException;

/**
 *
 * @author Administrator
 */
public interface DAOInterface<K, T> {
    abstract void insert(T data) throws VGBException;
    void update(T data) throws VGBException;
    public abstract void delete(T data) throws VGBException;
    public T get(K id) throws VGBException;
    //List<T> getAll();
}
