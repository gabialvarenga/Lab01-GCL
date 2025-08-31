package br.lab.util;

import java.io.*;
import java.util.*;

/**
 * Utilitário para salvar e carregar objetos em arquivos
 */
public class FileUtil {
    private static final String DATA_DIR = "data";
    
    /**
     * Salva um objeto em um arquivo
     * @param object Objeto a ser salvo
     * @param fileName Nome do arquivo
     */
    public static void saveObject(Object object, String fileName) {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(DATA_DIR + File.separator + fileName))) {
            out.writeObject(object);
            System.out.println("Dados salvos com sucesso em: " + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
    
    /**
     * Carrega um objeto de um arquivo
     * @param fileName Nome do arquivo
     * @return Objeto carregado ou null se falhar
     */
    public static Object loadObject(String fileName) {
        File file = new File(DATA_DIR + File.separator + fileName);
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado: " + fileName);
            return null;
        }
        
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(file))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Verifica se um arquivo existe
     * @param fileName Nome do arquivo
     * @return true se o arquivo existe, false caso contrário
     */
    public static boolean fileExists(String fileName) {
        File file = new File(DATA_DIR + File.separator + fileName);
        return file.exists();
    }
}
