package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {
        
        private Manche[] manche = new Manche[9];
        private int tour;
        private int[] lancer;
        private int mancheEnCours;
        private int[] dernierLancer;
        private int score;
        
	/**
	 * Constructeur
	 */
	public SinglePlayerGame() {
            tour = 0;
            lancer = new int[2];
            mancheEnCours = 0;
            dernierLancer = new int[3];
            score = 0;
	}

	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
            if(nombreDeQuillesAbattues < 0 && nombreDeQuillesAbattues > 10) {
                throw new IllegalArgumentException();
            }
            
            if(tour != 9) {
                if (mancheEnCours == 0 && nombreDeQuillesAbattues == 10) {
                    lancer[0] = nombreDeQuillesAbattues;
                    lancer[1] = 0;
                    manche[tour].AjoutScore(lancer);
                    tour += 1;
                } else if (mancheEnCours == 0) {
                   lancer[0] = nombreDeQuillesAbattues;
                    mancheEnCours = 1;
                } else {
                    lancer[1] = nombreDeQuillesAbattues;
                    mancheEnCours = 0;
                    manche[tour].AjoutScore(lancer);
                    tour += 1;
                }
            } else {
                if(mancheEnCours == 0) {
                    dernierLancer[0] = nombreDeQuillesAbattues;
                    mancheEnCours += 1;
                } else if(mancheEnCours == 1) {
                    dernierLancer[1] = nombreDeQuillesAbattues;
                    mancheEnCours += 1;
                } else if(mancheEnCours == 2 && dernierLancer[0] + dernierLancer[1] >= 10) {
                    dernierLancer[2] = nombreDeQuillesAbattues;
                }
            }
	}

	/**
	 * Cette méthode donne le score du joueur
	 *
	 * @return Le score du joueur
	 */
	public int score() {
            int[] enCasSpare = new int[2];
            score = manche[0].getScore();
            for(int i = 1 ; i < manche.length ; i++) {
                if(manche[i-1].getType() == Manche.Type.STRIKE) {
                    score += manche[i].getScore()*2;
                } else if(manche[i-1].getType() == Manche.Type.SPARE) {
                    enCasSpare = manche[i].getLancer();
                    score += enCasSpare[0]*2 + enCasSpare[1];
                } else if (manche[i-1].getType() == Manche.Type.RIEN) {
                    score += manche[i].getScore();
                }
            }
            
            for(int i = 0 ; i < dernierLancer.length ; i++) {
                score += dernierLancer[i];
            }
            
            return score;
	}
}
