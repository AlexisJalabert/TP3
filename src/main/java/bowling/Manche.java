/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

/**
 * Représente chaque manche d'une partie de bowling
 * @author ajalaber
 */
public class Manche {
    private int[] lancer = new int[2]; // représente le nombre de quilles tomber sur les 2 lancer de la manche
    private int score; // score atteint suite au 2 lancers de la manche
    protected enum Type {
        STRIKE,SPARE,RIEN
    }
    private Type type; // précise si le joueur a fait un strike, spare ou rien du tout sur cette manche
    
    /**
     * Constructeur
     */
    public Manche() {
        this.score = 0;
        this.type = Type.RIEN;
    }
    
    /**
     * Retourne le score de la manche
     * @return le score de la manche
     */
    public int getScore() {
        return this.score;
    }
    
    /**
     * Retourne le type de la manche
     * @return STRIKE, SPARE ou RIEN
     */
    public Type getType() {
        return this.type;
    }
    /**
     * Ajoute le score de chaque lancer et détermine s'il s'agit d'un strike, spare ou rien
     * @param lanc tableau de 2 entiers dans lequel sont référencés le résultats de chacun des lancers du joueur.
     */
    public void AjoutScore(int[] lanc) {
        lancer[0] = lanc[0];
        lancer[1] = lanc[1];
        estStrike();
        estSpare();
        score = lancer[0] + lancer[1];
    }
    /**
     * Détermine s'il  y a un strike ou non sur cette manche
     */
    private void estStrike() {
        if(lancer[0] == 10) {
            type = Type.STRIKE;
        }
    }
    /**
     * Détermine s'il ya un spare ou non sur cette manche
     */
    private void estSpare() {
        if(lancer[1] + lancer[0] == 10 && lancer[1] != 0) {
            type = Type.SPARE;
        }
    }
    
    public int[] getLancer() {
        return this.lancer;
    }
    
}
